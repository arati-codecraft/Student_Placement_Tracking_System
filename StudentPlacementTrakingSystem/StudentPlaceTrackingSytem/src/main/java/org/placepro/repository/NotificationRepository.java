package org.placepro.repository;

import java.util.List;

import org.placepro.model.Notification;

public interface NotificationRepository {

    void addNotification(int placementId, String status);

    List<Notification> getByStudentId(int studentId);
}