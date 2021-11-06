package com.company;

import java.util.Random;

public class Main {


    public static int bossHealth = 1000;
    public static int bossDamage = 80;
    public static String bossDefence = "";
    public static int[] heroesHealth = {300, 240, 250, 300, 700, 250, 280, 200};
    public static int[] heroesDamage = {20, 15, 25, 0, 5, 20, 25, 10};
    public static String[] heroesAttackType = {"Рыцарь", "Маг", "Стриказец", "Медик", "Голем", "Убивашка", "Балтек", "Уру"};
    public static int round_number = 0;
    public static Random random = new Random();


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) {
            bossHits();
        }
        Ubivashca();
        Golem();
        Baltek();
        heroesHit();
        UruVzakone();
        medikHill();
        VyjivshieHeross();
        printStatistics();

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    int coeff = random.nextInt(11); //0,1,2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;

                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }

    }

    public static void medikHill() {


        for (int i = 0; i < heroesAttackType.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += 35;
                System.out.println("### Medic zahilil - " + heroesAttackType[i] + " ###");
                break;

            }
        }


    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND ______________");
        System.out.println("\n" + "BOSS HEALTH: " + bossHealth + " (" + bossDamage + ")" + "\n");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
        System.out.println("____________________\n\n\n");
    }

    public static void Golem() {

        int uronOtBosa = bossDamage / 5;
        int jivHeroes = 0;

        if (heroesHealth[4] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 4) {
                    continue;
                } else if (heroesHealth[i] > 0) {
                    jivHeroes++;

                }
            }
            heroesHealth[4] -= uronOtBosa * jivHeroes;
            System.out.println("урон голему " + uronOtBosa * jivHeroes + "+" + bossDamage);

        }

    }

    public static void Ubivashca() {
        boolean uklonOtBossa = random.nextBoolean();
        if (heroesHealth[5] > 0) {
            if (uklonOtBossa) {
                heroesHealth[5] += bossDamage - 10;

                System.out.println("---Убивашка укланился--- ");

            }
        }
    }

    public static void VyjivshieHeross() {
        int kolvo = 0;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                kolvo++;
            }

        }
        System.out.println("! ОСТАЛОСЬ В ЖИВЫХ - " + kolvo + " героев !!!");

    }

    public static void Baltek() {
        int blocked = random.nextInt(80 - 10) + 10;
        if (heroesHealth[6] > 0) {
            heroesHealth[6] += blocked;
            bossHealth -= blocked;
            System.out.println(heroesAttackType[6] + " заблокировал " + blocked + "xp и нанес урон Босу " + blocked + "xp");
        }
    }
    public static void UruVzakone () {
        int zaglushka = random.nextInt(2);
        if (heroesHealth[7] > 0) {
            for (int i = 0; i < bossDamage; i++) {
                if (zaglushka == 1) {
                    System.out.println("Уру напал");
                    bossDamage = 0;
                    break;
                } else if (zaglushka == 2) {
                    System.out.println(" Уру промазал ");
                    break;
                }
            }
        }



    }

}