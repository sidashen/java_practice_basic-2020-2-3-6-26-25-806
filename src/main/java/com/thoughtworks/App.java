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
    String[] items = selectedItems.split(",");
    int[] itemIndex = getSelectedItemIndex(items);
    int[] itemCount = getSelectedItemCount(items);
    String[] itemName = getSelectedItemName(itemIndex);
    int[] itemPrice = getSelectedItemPrice(itemIndex);
    int[] singleItemTotal = getSingleTotalPrice(itemPrice, itemCount);
    String receipt = printReceipt(items, itemName, itemCount, singleItemTotal);
    return receipt;
  }

  public static int[] getSelectedItemIndex(String[] items) {
    String[] itemIds = getItemIds();
    int[] itemIndex = new int[items.length];
    for (int i = 0; i < items.length; i++) {
      for (int j = 0; j < itemIds.length; j++) {
        if (itemIds[j].equals(items[i].substring(0, 8))) {
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

  public static int[] getSelectedItemPrice(int[] itemIndex) {
    double[] itemPrices = getItemPrices();
    int[] price = new int[itemIndex.length];
    for (int i = 0; i < itemPrices.length; i++) {
      for (int j = 0; j < itemIndex.length; j++) {
        if (itemIndex[j] == i) {
          price[j] = (int) itemPrices[i];
        }
      }
    }
    return price;
  }

  public static int[] getSingleTotalPrice(int[] price, int[] itemCount) {
    int[] singleItemTotal = new int[price.length];
    for (int i = 0; i < price.length; i++) {
      singleItemTotal[i] = price[i] * itemCount[i];
    }
    return singleItemTotal;
  }

  public static ArrayList getHalfPriceItems(String[] itemName, int[] singleItemTotal) {
    String[] halfPriceIds = getHalfPriceIds();
    String[] itemIds = getItemIds();
    int[] halfPriceItemIndex = new int[halfPriceIds.length];

    for (int i = 0; i < halfPriceIds.length; i++) {
      for (int j = 0; j < itemIds.length; j++) {
        if (halfPriceIds[i].equals(itemIds[j])) {
          halfPriceItemIndex[i] = j;
        }
      }
    }

    String[] halfPriceItem = getSelectedItemName(halfPriceItemIndex);
    ArrayList <String> selectedHalfPriceItem = new ArrayList<>();
    for (int i = 0; i < itemName.length; i++) {
      for (int j = 0; j < halfPriceItem.length; j++) {
        if (itemName[i].equals(halfPriceItem[j])) {
          singleItemTotal[i] = singleItemTotal[i] / 2;
          selectedHalfPriceItem.add(itemName[i]);
        }
      }
    }
    return selectedHalfPriceItem;
  }

  public static String printReceipt(String[] items, String[] itemName, int[] itemCount, int[] singleItemTotal) {
    String itemInfo = "";
    String bonus = "";
    String receipt = "";
    int[] totalPriceDiscount = new int[2];
    double tempPrice = 0;
    for (int j = 0; j < items.length; j++) {
      tempPrice += singleItemTotal[j];
      itemInfo += itemName[j] + " x " + itemCount[j] + " = " + singleItemTotal[j] + "元\n";
    }

    List selectedHalfPriceItem = getHalfPriceItems(itemName, singleItemTotal);

    if (selectedHalfPriceItem.size() > 0) {
      for (int i = 0; i < items.length; i++) {
        totalPriceDiscount[0] += singleItemTotal[i];
      }
      totalPriceDiscount[1] = (int) (tempPrice - totalPriceDiscount[0]);
      String discountMeal = selectedHalfPriceItem.get(0) + "，" + selectedHalfPriceItem.get(1);
      bonus = "使用优惠:\n" + "指定菜品半价(" + discountMeal
        + ")，省" + totalPriceDiscount[1] + "元\n"
        + "-----------------------------------\n";
    } else if (tempPrice >= 30) {
      totalPriceDiscount[0] = (int) (tempPrice - 6);
      bonus = "使用优惠:\n"
        + "满30减6元，省6元\n"
        + "-----------------------------------\n";
    } else {
      totalPriceDiscount[0] = (int) tempPrice;
    }

    receipt = "============= 订餐明细 =============\n"
      + itemInfo
      + "-----------------------------------\n"
      + bonus
      + "总计：" + totalPriceDiscount[0] + "元\n"
      + "===================================";

    return receipt;
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
