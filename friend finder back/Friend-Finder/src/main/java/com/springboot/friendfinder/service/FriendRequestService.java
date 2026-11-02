package com.springboot.friendfinder.service;

import com.springboot.friendfinder.controllers.friendRequestVm.*;
import com.springboot.friendfinder.controllers.userFriends.UserFriends;

import java.util.List;

public interface FriendRequestService {
FriendResponseVm friendRequest(FriendRequestVm request);
List<FriendResponseVm> getALLFriendRequest(FriendRequestVm request, String status);
FriendResponseVm acceptFriendRequest(long senderId,long receiverId);
FriendResponseVm rejectFriendRequest(long senderId,long receiverId);
FriendsNotFollowed friendsNotFollowed();
NumberOfUserFollower aboutUserLogin();
List<FriendResponseForLoginUser> getAllFriendResponseForLoginUser();
List<UserFriends> getAllFriendsForLoginUser();
}
