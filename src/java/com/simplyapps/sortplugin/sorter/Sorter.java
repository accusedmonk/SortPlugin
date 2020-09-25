package com.simplyapps.sortplugin.sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Sorter {
  public int[] playerHotbarIndices = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
  public int[] playerInventoryIndices = {
    9,
    10,
    11,
    12,
    13,
    14,
    15,
    16,
    17,
    18,
    19,
    20,
    21,
    22,
    23,
    24,
    25,
    26,
    27,
    28,
    29,
    30,
    31,
    32,
    33,
    34,
    35,
  };

  public ItemStack[] sortPlayerInventory(ItemStack[] currentInventory) {
    ItemStack[] sortedInventory = currentInventory.clone();
    ArrayList<ItemStack> invList = new ArrayList<ItemStack>();

    for (
      int i = playerInventoryIndices[0];
      i < playerInventoryIndices[0] + playerInventoryIndices.length;
      i++
    ) {
      invList.add(currentInventory[i]);
    }

    sortByType(invList);
    invList = collapseSortedStacks(invList);

    for (int i = 0; i < invList.size(); i++) {
      sortedInventory[playerInventoryIndices[i]] = invList.get(i);
    }

    for (int i = invList.size(); i < playerInventoryIndices.length; i++) {
      sortedInventory[playerInventoryIndices[i]] = null;
    }

    return sortedInventory;
  }

  public ItemStack[] sortChestInventory(ItemStack[] currentInventory) {
    ItemStack[] sortedInventory = new ItemStack[currentInventory.length];
    ArrayList<ItemStack> invList = new ArrayList<ItemStack>();

    for (int i = 0; i < currentInventory.length; i++) {
      invList.add(currentInventory[i]);
    }

    sortByType(invList);
    invList = collapseSortedStacks(invList);

    for (int i = 0; i < invList.size(); i++) {
      sortedInventory[i] = invList.get(i);
    }

    return sortedInventory;
  }

  private ArrayList<ItemStack> collapseSortedStacks(
    ArrayList<ItemStack> itemList
  ) {
    ArrayList<ItemStack> newList = new ArrayList<ItemStack>();
    Material previousType = null;

    for (int i = 0; i < itemList.size() - 1; i++) {
      ItemStack currentStack = itemList.get(i);

      if (
        currentStack == null || currentStack.getType() == previousType
      ) continue;

      previousType = currentStack.getType();

      int nextIndex = i + 1;
      int currentTotalAmount = currentStack.getAmount();
      ItemStack nextStack = itemList.get(nextIndex);

      while (nextStack != null && nextStack.isSimilar(currentStack)) {
        currentTotalAmount += nextStack.getAmount();
        nextStack = itemList.get(++nextIndex);
      }

      int fullStackCount = currentTotalAmount / currentStack.getMaxStackSize();

      int remainder = currentTotalAmount % currentStack.getMaxStackSize();

      for (int j = 0; j < fullStackCount; j++) {
        var newStack = currentStack.clone();
        newStack.setAmount(newStack.getMaxStackSize());
        newList.add(newStack);
      }

      if (remainder > 0) {
        var newStack = currentStack.clone();
        newStack.setAmount(remainder);
        newList.add(newStack);
      }
    }

    return newList;
  }

  private void sortByType(ArrayList<ItemStack> itemList) {
    Collections.sort(
      itemList,
      new Comparator<ItemStack>() {

        public int compare(ItemStack o1, ItemStack o2) {
          if (o1 == null) return 1;
          if (o2 == null) return -1;

          return o1
            .getType()
            .name()
            .toLowerCase()
            .compareTo(o2.getType().name().toLowerCase());
        }
      }
    );
  }
}
