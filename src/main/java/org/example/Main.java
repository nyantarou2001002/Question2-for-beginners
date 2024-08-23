package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  static class Student {
    String name;
    int score;

    Student(String name, int score) {
      this.name = name;
      this.score = score;
    }

    @Override
    public String toString() {
      return  name + ": " + score + "点";
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Student> students = new ArrayList<>();
    boolean running = true;

    while (running) {
      System.out.println("\n入力例:");
      System.out.println("1. 学生を追加");
      System.out.println("2. 学生を削除");
      System.out.println("3. 点数を更新");
      System.out.println("4. 平均点を計算");
      System.out.println("5. 全学生の情報を表示");
      System.out.println("6. 終了");
      System.out.print("選択してください: ");
      String choice = scanner.next();
      scanner.nextLine();  // 改行を消費

      switch (choice) {
        case "1":
          // 名前と点数を追加
          System.out.print("学生の名前を入力してください: ");
          String nameToAdd = scanner.nextLine();
          System.out.print(nameToAdd + "の点数を入力してください: ");
          String preScoreToAdd = scanner.next();
          boolean result = true;
          for(int i=0; i<preScoreToAdd.length(); i++){
            if(Character.isDigit(preScoreToAdd.charAt(i))){
              continue;
            }else{
              result = false;
              break;
            }
          }
          int scoreToAdd;

          if(result){
            scoreToAdd = Integer.parseInt(preScoreToAdd);
          }else{
            System.out.println("無効な入力です。もう一度入力してください。");
            break;
          }

          students.add(new Student(nameToAdd, scoreToAdd));
          System.out.println(nameToAdd + "を追加しました。");
          break;

        case "2":
          // 名前を削除
          System.out.print("削除する学生の名前を入力してください: ");
          String nameToRemove = scanner.nextLine().trim();
          boolean removed = false;
          for(Student student : students) {
            if (student.name.equals(nameToRemove)) {
              students.remove(student);
              removed = true;
              System.out.println(nameToRemove + "を削除しました。");
              break;
            }
          }

          if(!removed){
            System.out.println(nameToRemove + "が見つかりませんでした。");
          }
          break;



        case "3":
          // 点数を更新
          System.out.print("点数を更新する学生の名前を入力してください: ");
          String nameToUpdate = scanner.nextLine();
          System.out.print("新しい点数を入力してください: ");
          String preNewScore = scanner.next();
          boolean result2 = true;
          for(int i=0; i<preNewScore.length(); i++){
            if(Character.isDigit(preNewScore.charAt(i))){
              continue;
            }else{
              result2 = false;
              break;
            }
          }
          int newScore;

          if(result2){
            newScore = Integer.parseInt(preNewScore);
          }else{
            System.out.println("無効な入力です。もう一度入力してください。");
            break;
          }




          boolean updated = false;
          for (Student student : students) {
            if (student.name.equals(nameToUpdate)) {
              student.score = newScore;
              updated = true;
              System.out.println(nameToUpdate + "の点数を" + newScore + "に更新しました。");
              break;
            }
          }
          if (!updated) {
            System.out.println(nameToUpdate + "が見つかりませんでした。");
          }
          break;

        case "4":
          // 平均点を計算
          if (students.isEmpty()) {
            System.out.println("学生のデータがありません。");
          } else {
            double average = students.stream()
                .mapToInt(student -> student.score)
                .average()
                .orElse(0.0);
            System.out.println("平均点: " + average + "点");
          }
          break;

        case "5":
          // 全学生の情報を表示
          if (students.isEmpty()) {
            System.out.println("学生のデータがありません。");
          } else {
            System.out.println("学生一覧:");
            students.forEach(student -> System.out.println(student));
          }
          break;

        case "6":
          // 終了
          running = false;
          System.out.println("プログラムを終了します。");
          break;

        default:
          System.out.println("無効な選択です。もう一度入力してください。");
          break;
      }
    }
    scanner.close();
  }
}
