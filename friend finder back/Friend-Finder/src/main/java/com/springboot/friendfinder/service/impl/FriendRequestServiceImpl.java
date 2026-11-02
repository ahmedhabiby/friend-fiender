package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.controllers.friendRequestVm.*;
import com.springboot.friendfinder.controllers.userFriends.UserFriends;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.enums.FriendsEnums;
import com.springboot.friendfinder.mapper.FriendRequestMapper;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.FriendRequest;
import com.springboot.friendfinder.model.profile;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.repo.FriendRequestRepo;
import com.springboot.friendfinder.repo.ProfileRepo;
import com.springboot.friendfinder.repo.UserRepo;
import com.springboot.friendfinder.service.FriendRequestService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    private final UserRepo userRepo;
    private UserService userService;
    private UserMapper userMapper;
    private FriendRequestMapper friendRequestMapper;
    private FriendRequestRepo friendRequestRepo;
    private ProfileRepo profileRepo;

    @Autowired
    public FriendRequestServiceImpl(UserService userService,ProfileRepo profileRepo, UserMapper userMapper, FriendRequestMapper friendRequestMapper, FriendRequestRepo friendRequestRepo, UserRepo userRepo) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.friendRequestMapper = friendRequestMapper;
        this.friendRequestRepo = friendRequestRepo;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }
    @Override
    @CacheEvict(value = "request", allEntries = true)
    public FriendResponseVm friendRequest(FriendRequestVm request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto = userService.findUserByUsername(username);
        users user1=userMapper.toEntity(usersDto);
        if(usersDto.getUsername().equals(request.getReceiverName())) {
            throw new RuntimeException("you are sent request to yourself");
        }
        usersDto usersDto1 = userService.findUserByUsername(request.getReceiverName());
        users user2=userMapper.toEntity(usersDto1);
        if((friendRequestRepo.findBySenderAndReceiver(user1.getId(), user2.getId()))!=null) {
            throw new RuntimeException("Request is already exsist");
        }
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(user1);
        friendRequest.setReceiver(user2);
        friendRequest.setStatus(FriendsEnums.Pending);
        friendRequestRepo.save(friendRequest);
        return new FriendResponseVm(user1.getId(),user2.getId(),user1.getUsername(),user2.getUsername());
    }

    @Override
    @Cacheable(value = "request",key = "'request'+#request.receiverName")
    public List<FriendResponseVm> getALLFriendRequest(FriendRequestVm request, String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto = userService.findUserByUsername(username);
        users user1=userMapper.toEntity(usersDto);
        usersDto usersDto1 = userService.findUserByUsername(request.getReceiverName());
        users user2=userMapper.toEntity(usersDto1);
        FriendsEnums friendsEnums=FriendsEnums.valueOf(status);
        List<FriendRequest> friendRequests= friendRequestRepo.findFriendRequestById(user2.getId(),friendsEnums);
        return friendRequests.stream().map(res->new FriendResponseVm(user1.getId(),user2.getId(),res.getSender().getUsername(),res.getReceiver().getUsername())).toList();

    }

    @Override
    public FriendResponseVm acceptFriendRequest(long senderId, long receiverId) {
        FriendRequest friendRequest=friendRequestRepo.findBySenderAndReceiver(senderId, receiverId);
        friendRequest.setStatus(FriendsEnums.Accepted);
        friendRequestRepo.save(friendRequest);
        return new FriendResponseVm(senderId,receiverId,friendRequest.getSender().getUsername(),friendRequest.getReceiver().getUsername());
    }

    @Override
    public FriendResponseVm rejectFriendRequest(long senderId, long receiverId) {
        FriendRequest friendRequest=friendRequestRepo.findBySenderAndReceiver(senderId, receiverId);
        friendRequest.setStatus(FriendsEnums.Rejected);
        friendRequestRepo.save(friendRequest);
        return new FriendResponseVm(senderId,receiverId,friendRequest.getSender().getUsername(),friendRequest.getReceiver().getUsername());
    }

  @Override
public FriendsNotFollowed friendsNotFollowed() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    usersDto currentUserDto = userService.findUserByUsername(username);
    users currentUser = userMapper.toEntity(currentUserDto);

    // Get friends IDs
    List<Long> senderIds = friendRequestRepo.findFriendRequestByAcceptByReceiverId(currentUser.getId());
    List<Long> receiverIds = friendRequestRepo.findFriendRequestByAcceptBySenderId(currentUser.getId());

    Set<Long> friendIds = new HashSet<>();
    friendIds.addAll(senderIds);
    friendIds.addAll(receiverIds);

    List<users> usersNotFollowed = userRepo.findAll()
            .stream()
            .filter(user -> !user.getId().equals(currentUser.getId()))
            .filter(user -> !friendIds.contains(user.getId()))
            .collect(Collectors.toList());

    List<String> userNames = usersNotFollowed.stream()
            .map(users::getUsername)
            .collect(Collectors.toList());

    List<String> imageUrls = usersNotFollowed.stream()
            .map(user -> {
                profile profile = profileRepo.findprofileByUserId(user.getId());
                if (profile != null) {
                    return profile.getImageUrl();
                }
                return "null";   // or "" or "default-profile.png"
            })
            .collect(Collectors.toList());

    return new FriendsNotFollowed(userNames, imageUrls);
}

    @Override
    @CachePut(value = "request",key = "'about'+#result.userName")
    public NumberOfUserFollower aboutUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto = userService.findUserByUsername(username);
        users user1=userMapper.toEntity(usersDto);
        long numberOfFollowers=friendRequestRepo.countFollowers(user1.getId());
        long numberOfFollowers2=friendRequestRepo.countFollowers2(user1.getId());
        return new NumberOfUserFollower(username,numberOfFollowers+numberOfFollowers2);

    }

    @Override
    public List<FriendResponseForLoginUser> getAllFriendResponseForLoginUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        users user = userMapper.toEntity(
                userService.findUserByUsername(username)
        );

        List<FriendRequest> friendRequests =
                friendRequestRepo.findFriendRequestForLoginUser(user.getId());

        return friendRequests.stream()
                .map(request -> new FriendResponseForLoginUser(
                        request.getSender().getUsername(),
                        request.getCreatedAt(),
                        request.getSender().getId(),
                        request.getReceiver().getId()
                ))
                .toList();
    }

   @Override
public List<UserFriends> getAllFriendsForLoginUser() {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String username = authentication.getName();

    users loginUser =
            userMapper.toEntity(
                    userService.findUserByUsername(username)
            );

    List<FriendRequest> friendRequests =
            friendRequestRepo.findFriendsForUser(loginUser.getId());

    return friendRequests.stream()
            .map(request -> {

                users friend =
                        request.getSender().getId().equals(loginUser.getId())
                                ? request.getReceiver()
                                : request.getSender();

                profile friendProfile =
                        profileRepo.findprofileByUserId(friend.getId());

                return new UserFriends(
                        friendProfile != null
                        ?friendProfile.getImageUrl()
                        :"no image",
                        friend.getUsername(),
                        friendProfile != null
                                ? friendProfile.getJob()
                                : "No Job"
                );
            })
            .toList();
}


}
