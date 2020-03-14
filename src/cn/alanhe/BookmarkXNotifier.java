package cn.alanhe;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class BookmarkXNotifier {
    private final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("BookMarkX", NotificationDisplayType.BALLOON, false);

    public Notification notify(Project project, String content) {
        final Notification notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.INFORMATION);
        notification.notify(project);
        return notification;
    }
}
