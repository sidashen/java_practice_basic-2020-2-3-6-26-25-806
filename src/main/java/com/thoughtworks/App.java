package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    // 此处补全代码
    return receipt;
  }

  public static int[] getSelectedItemIndex(String[] items) {
    String[] itemIds = getItemIds();
    int[] itemIndex = new int[items.length];
    for (int i = 0; i < items.length; i++) {
      for (int j = 0; j < itemIds.length; j++) {
        if (items[i].substring(0, 8) == itemIds[j]) {
          itemIndex[i] = j;
        }
      }
    }
    return itemIndex;
  }

  public static int[] getSelectedItemCount(String[] items) {
    int[] itemCount = new int[items.length];
    for (int i = 0; i < items.length; i++) {
      itemCount[i] = Integer.parseInt(items[i].substring(11));
    }
    return itemCount;
  }

  public static String[] getSelectedItemName(int[] itemIndex) {
    String[] itemNames = getItemNames();
    String[] itemName = new String[itemIndex.length];
    for (int i = 0; i < itemIndex.length; i++) {
      for (int j = 0; j < itemNames.length; j++) {
        if (itemIndex[i] == j) {
          itemName[i] = itemNames[j];
        }
      }
    }
    return itemName;
  }



  /**
   * 获取每个菜品依次的编号
   */
  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }

  /**
   * 获取每个菜品依次的名称
   */
  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  /**
   * 获取每个菜品依次的价格
   */
  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  /**
   * 获取半价菜品的编号
   */
  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }
}
