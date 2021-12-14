package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static double coolingFactor = 0.5;
    private static Scanner sc = new Scanner(System.in);

    //Вычисляем фрактал для получения максимальной температуры
    public static int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return f * getFactorial(f - 1);
        }
    }

    //Создаем отделы со знанием координант
    public static List<Section> addSectionsKNW(int size, List<Section> listOfSections){
        for (int i = 0; i < size; i++) {
            String xy;
            int x, y;
            System.out.print("Введите название отдела №" + (i + 1) + ", который хотите посетить: ");
            String temp_name = sc.next();
            System.out.print("Введите координату X отдела "+temp_name+" (0-9): ");
            x = sc.nextInt();
            System.out.print("Введите координату Y отдела "+temp_name+" (0-9): ");
            y = sc.nextInt();
            Section temp_obj = new Section(temp_name, x, y);
            listOfSections.add(temp_obj);
        }
        return listOfSections;
    }

    //Создаем отделы без знания координат
    public static List<Section> addSectionsRND(int size, List<Section> listOfSections){
        for (int i = 0; i < size; i++) {
            System.out.print("Введите название отдела №" + (i + 1) + ", который хотите посетить: ");
            String temp_name = sc.next();
            Section temp_obj = new Section(temp_name);
            listOfSections.add(temp_obj);
        }
        return listOfSections;
    }


    public static void main(String[] args) {
        List<Section> listOfSections = new ArrayList<Section>();
        int size;

        //Задаем точки
        System.out.print("Введите количество отделов, которые хотите посетить: ");
        size = sc.nextInt();

        if (size <= 1){
            System.out.println("Путь будет занимать +-0 :_)");
            return;
        }

        System.out.print("Вы знаете расположение отделов?\n\t1. Да; 2. Нет.\n\t");
        int choise = sc.nextInt();
        switch (choise) {
            case 1:
                listOfSections = addSectionsKNW(size, listOfSections);
                break;
            case 2:
                listOfSections = addSectionsRND(size, listOfSections);
                break;
            default:
                System.out.println("Вы ошиблись при вводе, будем считать, что вы не знаете расположение отделов.");
                listOfSections = addSectionsRND(size, listOfSections);
                break;
        }

        //Выводим матрицу
        System.out.println("Матрица со всеми отделами (где 0 - пустота, X - отдел): ");
        String temp;
        System.out.println(" X 0 1 2 3 4 5 6 7 8 9");
        System.out.println("Y --------------------");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 10; j++) {
                temp = " 0";
                for (int n = 0; n != listOfSections.size(); n++) {
                    if (j == (listOfSections.get(n).getX()) && i == (listOfSections.get(n).getY())) {
                        temp = " X";
                    }
                }
                System.out.print(temp);
            }
            System.out.println();
        }

        //Создаем два маршурта для реализации алгоритма (текущий и лучший) и вычисляем макс. температуру
        ShoppingWay current = new ShoppingWay(listOfSections);
        ShoppingWay best = current.duplicate();
        double temperature = getFactorial(listOfSections.size()) + 10;

        //Алогритм имитации отжига
        for (double t = temperature; t > 0; t -= coolingFactor) {
            ShoppingWay neighbor = current.duplicate();

            int index1 = (int) (neighbor.waySize() * Math.random());
            int index2 = (int) (neighbor.waySize() * Math.random());

            Collections.swap(listOfSections, index1, index2);

            int currentLength = current.getWayLenght();
            int neighborLength = neighbor.getWayLenght();

            if (Math.random() < Formula.probability(currentLength, neighborLength, t)) {
                current = neighbor.duplicate();
            }

            if (current.getWayLenght() < best.getWayLenght()) {
                best = current.duplicate();
            }

            //System.out.println(current+""+currentLength);
        }

        System.out.println("Весь маршрут: " + best + ", его длина: " + best.getWayLenght());


    }
}
