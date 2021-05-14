package com.getbuddies.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getbuddies.app.model.RoomUsers;

@Repository
public interface RoomUsersRepo extends JpaRepository<RoomUsers, Long> {

}
