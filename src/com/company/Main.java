package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

        public static int getFactorial(int f) {
            if (f <= 1) {
                return 1;
            }
            else {
                return f * getFactorial(f - 1);
            }
        }

        private static double coolingFactor = 0.5;

        public static void main(String[] args) {
            List<Section> listOfSections = new ArrayList<Section>();
            int size;
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите количество отделов, которые хотите посетить: ");
            size = sc.nextInt();

            for (int i =0; i <size; i++){
                System.out.print("Введите название отдела, который хотите посетить: ");
                String temp_name = sc.next();
                Section temp_obj = new Section(temp_name);
                listOfSections.add(temp_obj);
            }

//                Section place1 = new Section("Продукты", 8, 7);
//                Section place2 = new Section("Вода", 3, 3);
//                Section place3 = new Section("Кириллица", 9, 4);
//                listOfSections.add(place1);
//                listOfSections.add(place2);
//                listOfSections.add(place3);

                ShoppingWay current = new ShoppingWay(listOfSections);
                ShoppingWay best = current.duplicate();
                double temperature = getFactorial(listOfSections.size()) + 10;

                for (double t = temperature; t > 0; t -= coolingFactor) {
                    ShoppingWay neighbor = current.duplicate();

                    int index1 = (int) (neighbor.waySize() * Math.random());
                    int index2 = (int) (neighbor.waySize() * Math.random());

                    Collections.swap(listOfSections, index1, index2);

                    int currentLength = current.getTourLength();
                    int neighborLength = neighbor.getTourLength();

                    if (Math.random() < Formula.probability(currentLength, neighborLength, t)) {
                        current = neighbor.duplicate();
                    }

                    if (current.getTourLength() < best.getTourLength()) {
                        best = current.duplicate();
                    }

                    //System.out.println(current+""+currentLength);
                }

                System.out.println(": " + best.getTourLength());
                System.out.println("Весь маршрут: " + best + ", его длина: " + best.getTourLength());

                String temp;
                System.out.println(" X 0 1 2 3 4 5 6 7 8 9");
                System.out.println("Y --------------------");
                for (int i = 0; i < 10; i++){
                    System.out.print(i+"|");
                    for (int j = 0; j < 10; j++){
                        temp = " 0";
                        for (int n = 0; n != listOfSections.size(); n++) {
                            if (j==(listOfSections.get(n).getX()) && i==(listOfSections.get(n).getY()))
                            {
                                temp = " X";
                            }
                        }
                        System.out.print(temp);
                    }
                    System.out.println();
                }

            }
        }
