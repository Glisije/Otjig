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
            List listOfSections = new ArrayList<>();
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

            ShoppingWay current = new ShoppingWay(listOfSections);
            ShoppingWay best = current.duplicate();
            double temperature = getFactorial(listOfSections.size())+10;

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
            System.out.println("Весь маршрут: " + best+ ", его длина: "+best.getTourLength());
        }
    }