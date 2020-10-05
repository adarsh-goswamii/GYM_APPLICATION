package com.example.gym_application;
import java.util.ArrayList;

public class Utils
{
    public static ArrayList<Training> trainings;
    public static ArrayList<Plan> plans;

    public static boolean addPlan(Plan plan)
    {
        if(plans== null)
            plans= new ArrayList<>();

        return plans.add(plan);
    }

    public static void initTrainings()
    {
        if(trainings== null)
            trainings= new ArrayList<>();

        Training benchPress= new Training("A compound workout that targets your chest, as it is a push workout your shoulder and triceps are also activated", "", "https://i.pinimg.com/564x/4b/00/0e/4b000ec7da8304fc27853429803a204d.jpg", "benchPress", 1);
        Training dips= new Training("A bodyweight workout best to target lower pecs when body is inclined and can also target triceps.", "", "https://i.pinimg.com/474x/d9/2b/2f/d92b2fa137632288fe802b215684a4b6.jpg", "Dips", 2);
        Training pullsUps= new Training("A compound bodyweight workout which is very effective for targeting back and biceps, a pull workout", "", "https://i.pinimg.com/474x/e0/78/cf/e078cfb1e3536c38d88ab9efffa45458.jpg", "Pull ups", 3);
        Training squat= new Training("A compound bodyweight workout which is very effective for targeting back and biceps, a pull workout", "", "https://i.pinimg.com/474x/2f/a9/8c/2fa98cdde9eb455def3ff6dfa0f14f5f.jpg", "Squats", 4);
        Training flys= new Training("A compound bodyweight workout which is very effective for targeting back and biceps, a pull workout", "", "https://i.pinimg.com/474x/39/59/ef/3959ef21c5bb47f216a9b07857051e7a.jpg", "Cable flys", 5);
        Training curls= new Training("A compound bodyweight workout which is very effective for targeting back and biceps, a pull workout", "", "https://i.pinimg.com/474x/37/ea/dc/37eadc5139c1ed98fd3a48b33c81b6a5.jpg", "barbell curls", 6);
        Training crunches= new Training("A compound bodyweight workout which is very effective for targeting back and biceps, a pull workout", "Your core consists not only of your abs. It also includes your oblique muscles on the sides of your trunk, as well as the muscles in your pelvis, lower back, and hips. Together, these muscles help stabilize your body.\n" +
                "\n" +
                "While the crunch is a popular core move, it isn’t safe for everyone. It can place a lot of stress on your back and neck, and it only works your abs, not the other muscles in your core", "https://i.pinimg.com/474x/19/c8/99/19c8997a20077d24ef0e7fc446d677b5.jpg", "Crunches", 7);
        Training calf= new Training("A compound bodyweight workout which is very effective for targeting back and biceps, a pull workout", "", "https://i.pinimg.com/474x/d9/2c/6e/d92c6e31bcc19af5dcc75891a6ea5d4d.jpg", "Donkey calf raise", 8);

        trainings.add(benchPress);
        trainings.add(dips);
        trainings.add(pullsUps);
        trainings.add(squat);
        trainings.add(flys);
        trainings.add(curls);
        trainings.add(crunches);
        trainings.add(calf);
    }

    public static ArrayList<Training> getTrainings() {
        return trainings;
    }

    public static ArrayList<Plan> getPlans() {
        return plans;
    }
}
