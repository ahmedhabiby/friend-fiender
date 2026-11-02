package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.enums.FriendsEnums;
import com.springboot.friendfinder.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {
    @Query("SELECT fr FROM FriendRequest fr " +
            "WHERE fr.sender.id = :senderId AND fr.receiver.id = :receiverId")
    FriendRequest findBySenderAndReceiver(Long senderId, Long receiverId);
    @Query("SELECT fr FROM FriendRequest fr " +
            "WHERE fr.receiver.id = :id AND fr.status = :status")
    List<FriendRequest> findFriendRequestById(Long id, FriendsEnums status);
    @Query("SELECT fr.sender.id FROM FriendRequest fr " +
            "WHERE fr.receiver.id = :id AND fr.status ='Accepted'")
    List<Long> findFriendRequestByAcceptByReceiverId(Long id);
    @Query("SELECT fr.receiver.id FROM FriendRequest fr " +
            "WHERE fr.sender.id = :id AND fr.status ='Accepted'")
    List<Long> findFriendRequestByAcceptBySenderId(Long id);
    @Query("SELECT COUNT(fr) FROM FriendRequest fr " +
            "WHERE fr.receiver.id = :id AND fr.status = 'Accepted'")
    long countFollowers(Long id);
    @Query("SELECT COUNT(fr) FROM FriendRequest fr " +
            "WHERE fr.sender.id = :id AND fr.status = 'Accepted'")
    long countFollowers2(Long id);
    @Query("select r from FriendRequest r where r.receiver.id=:receiverId and r.status='Pending'")
    List<FriendRequest> findFriendRequestForLoginUser(long receiverId);

    @Query("""
    SELECT fr
    FROM FriendRequest fr
    WHERE fr.status = 'Accepted'
    AND (fr.sender.id = :userId OR fr.receiver.id = :userId)
""")
    List<FriendRequest> findFriendsForUser( Long userId);

}
