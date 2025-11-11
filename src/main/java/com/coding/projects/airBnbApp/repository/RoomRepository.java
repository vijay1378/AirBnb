package com.coding.projects.airBnbApp.repository;

import com.coding.projects.airBnbApp.entity.Inventory;
import com.coding.projects.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
