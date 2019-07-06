package ru.xpendence.slimer.util.temp;

import ru.xpendence.slimer.base.ActivityType;
import ru.xpendence.slimer.entity.Activity;
import ru.xpendence.slimer.repository.ActivityRepository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 06.07.19
 * Time: 14:45
 * e-mail: v.chernyshov@pflb.ru
 */
//@Component
public class ActivitiesFill {

    private final ActivityRepository activityRepository;

    public ActivitiesFill(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

//    @Scheduled(initialDelay = 1000, fixedDelay = 1000000)
    public void parse() {
        try (InputStream inputStream = new FileInputStream("/home/v-chernyshov/activities.txt")) {
            List<Activity> activities = new ArrayList<>();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();

            while (line != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, "=");
                activities.add(
                        new Activity(
                                stringTokenizer.nextToken().replace("_", " "),
                                ActivityType.ENTERTAINMENT,
                                Integer.parseInt(stringTokenizer.nextToken()))
                );
                line = bufferedReader.readLine();
            }
            activityRepository.saveAll(activities);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
