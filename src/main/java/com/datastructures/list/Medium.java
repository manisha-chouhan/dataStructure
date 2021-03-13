package com.datastructures.list;

import java.util.*;

public class Medium {

    public class ListNode {
      int val;
      ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode prev = null;
        ListNode node = head;

        while(node != null) {
            ListNode temp = node.next;
            if(temp != null && node.val == temp.val) {
                while(temp != null && temp.val == node.val) {
                    temp = temp.next;
                }
                if(prev == null) {
                    head = temp;
                }
                else {
                    prev.next = temp;
                }
                node = temp;
            }
            else {
                prev = node;
                node = node.next;
            }

        }
        return head;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    //https://leetcode.com/problems/linked-list-in-binary-tree/
    List<TreeNode> starting = new ArrayList<>();
    public boolean isSubPath(ListNode head, TreeNode root) {
        findStartingPoints(head, root);

        for(TreeNode node : starting) {
            if(traverse(head, node)) return true;
        }
        return false;
    }

    public void findStartingPoints(ListNode head, TreeNode root) {
        if(root == null) return;
        if(root.val == head.val) starting.add(root);
        findStartingPoints(head, root.left);
        findStartingPoints(head, root.right);
    }

    public boolean traverse(ListNode head, TreeNode root) {
        if(head == null) return true;
        if(root == null) return false;

        if(root.val != head.val) {
            return false;
        }

        return traverse(head.next, root.left) || traverse(head.next, root.right);
    }

    //https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = null;
        ListNode prev = null;
        int carry = 0;
        while(l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum /10;
            sum = sum % 10;
            if(l3 == null) {
                l3 = new ListNode(sum);
                prev = l3;
            } else {
                ListNode node = new ListNode(sum);
                prev.next = node;
                prev = node;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            int sum = l1.val + carry;
            carry = sum /10;
            sum = sum % 10;
            if(l3 == null) {
                l3 = new ListNode(sum);
                prev = l3;
            } else {
                ListNode node = new ListNode(sum);
                prev.next = node;
                prev = node;
            }
            l1 = l1.next;
        }

        while(l2 != null) {
            int sum = l2.val + carry;
            carry = sum /10;
            sum = sum % 10;
            if(l3 == null) {
                l3 = new ListNode(sum);
                prev = l3;
            } else {
                ListNode node = new ListNode(sum);
                prev.next = node;
                prev = node;
            }
            l2 = l2.next;
        }

        if(carry != 0) {
            ListNode node = new ListNode(carry);
            prev.next = node;
        }

        return l3;
    }

    //https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while(slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == null || fast == null || fast.next == null) return null;
            if(slow == fast) break;
        }

        slow = head;
        while(slow != null && fast != null) {
            if(slow == fast) return slow;
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //https://leetcode.com/problems/reverse-linked-list-ii/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null;

        int i = 1;
        ListNode node = head;
        ListNode prev = null;

        while(node != null && i < m) {
            i++;
            prev = node;
            node = node.next;
        }
        //System.out.println(node.val + " and " + prev.val );
        ListNode mNode = node;
        ListNode _prev = null;

        while(node != null && i <= n) {
            i++;

            if(_prev == null) {
                //System.out.println(node.val + " and _prev is null");
                _prev = node;
                node = node.next;
            }
            else {
                //System.out.println(node.val + " and " + _prev.val );
                ListNode temp = node.next;
                node.next = _prev;
                _prev = node;
                node = temp;
            }
        }
        if(prev == null) head = _prev;
        else prev.next = _prev;
        mNode.next = node;

        return head;
    }

    //https://leetcode.com/problems/copy-list-with-random-pointer/
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node _head = null;
        HashMap<Node, Node> map = new HashMap<>();

        Node node = head;
        Node prev = null;

        while(node != null) {
            Node temp = new Node(node.val);
            if(prev == null) {
                _head = temp;
                prev = _head;
            }
            else {
                prev.next = temp;
                prev = temp;
            }
            map.put(node, temp);
            node = node.next;
        }
        node = head;
        Node temp = _head;

        while(node != null) {
            if(node.random == null) {
                temp = temp.next;
                node = node.next;
                continue;
            }
            Node random = node.random;
            random = map.get(random);
            temp.random = random;
            temp = temp.next;
            node = node.next;
        }
        return _head;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //https://leetcode.com/problems/sort-list/
    public ListNode sortList(ListNode head) {
        if(head == null) return null;
        TreeMap<Integer, ListNode> map = new TreeMap<>();
        ListNode result = head;
        ListNode prev = head;
        ListNode node = head.next;
        map.put(head.val, head);

        while(node != null) {
            Integer key = map.floorKey(node.val);
            ListNode temp = null;
            if(key == null) {
                temp = node;
                prev.next = node.next;
                node.next = result;
                result = temp;
                node = prev.next;
                map.put(temp.val, temp);
            }
            else {
                temp = map.get(key);
                if(prev == temp) {
                    temp = prev = node;
                    node = node.next;
                    map.put(temp.val, temp);
                }
                else {
                    prev.next = node.next;
                    node.next = temp.next;
                    temp.next = node;
                    node = prev.next;
                    map.put(temp.next.val, temp.next);
                }
            }
        }
        return result;
    }

    //https://leetcode.com/problems/partition-list/
    public ListNode partition(ListNode head, int x) {
        if(head == null)
            return null;
        if(head.next == null) return head;

        ListNode big = null;
        ListNode node = head;
        ListNode bHead = null;
        ListNode prev = null;

        while(node != null) {
            if(node.val >= x) {
                if(node == head) {
                    head = head.next;
                    if(big == null) {
                        bHead = node;
                    }
                    else {
                        big.next = node;
                    }
                    big = node;
                    big.next = null;
                    node = head;
                }
                else {
                    prev.next = node.next;
                    if(big == null) {
                        bHead = node;
                    }
                    else {
                        big.next = node;
                    }
                    //big.next = node;
                    node.next = null;
                    big = node;
                    node = prev.next;
                }
            }
            else {
                prev = node;
                node = node.next;
            }
        }
        if(prev != null)
            prev.next = bHead;
        else head = bHead;
        return head;
    }

    //https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
    ListNode head;
    HashMap<Integer, ListNode> map = new HashMap<>();
    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;

        int len = findSize(head);
        int l = 0;
        int r = len - 1;
        TreeNode root = convert(head, l, r);
        return root;
    }

    public TreeNode convert(ListNode head, int l, int r) {
        if(l > r) return null;
        int mid = l + (r - l)/2;
        ListNode node = map.get(mid);
        TreeNode root = new TreeNode(node.val);
        root.left = convert(head, l, mid - 1);
        root.right = convert(head, mid + 1, r);
        return root;
    }

    public int findSize(ListNode head) {
        int len = 0;
        ListNode node = head;
        while(node != null) {
            map.put(len, node);
            len++;
            node = node.next;
        }
        return len;
    }

    //https://leetcode.com/problems/split-linked-list-in-parts/
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode node = root;
        int len = 0;
        while(node != null) {
            node = node.next;
            len++;
        }
        ListNode[] arr = new ListNode[k];

        int rem = len % k;

        node = root;
        int i = 0;
        while(node != null && i < arr.length) {
            int j = 0;
            ListNode temp = node;
            ListNode prev = null;
            while(temp != null && j < len/k) {
                prev = temp;
                temp = temp.next;
                j++;
            }
            if(rem != 0) {
                rem--;
                prev = temp;
                temp = temp.next;
            }

            arr[i++] = node;
            node = temp;
            prev.next = null;
        }

        return arr;
    }

    //https://leetcode.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        ListNode node = head;
        ListNode prev = null;

        while(node != null && node.next != null) {
            if(prev == null) {
                head = node.next;
            }
            else {
                prev.next = node.next;
            }
            ListNode temp = node.next;
            node.next = temp.next;
            temp.next = node;
            prev = node;
            node = node.next;
        }
        return head;
    }

    //https://leetcode.com/problems/next-greater-node-in-linked-list/
    public int[] nextLargerNodes(ListNode head) {
        ListNode node = head;
        int len = 0;
        while(node != null) {
            len++;
            node = node.next;
        }

        int[] result = new int[len];

        Stack<int[]> stack = new Stack<>();
        int i = 0;
        node = head;
        while(node != null) {
            //System.out.println(node.val);
            if(stack.isEmpty() || node.val <= stack.peek()[0]) {
                stack.push(new int[]{node.val, i});
            }
            else {
                while(!stack.isEmpty() && node.val > stack.peek()[0]) {
                    int[] temp = stack.pop();
                    result[temp[1]] = node.val;
                }
                stack.push(new int[]{node.val, i});
            }
            node = node.next;
            i++;
        }

        while(!stack.isEmpty()) {
            int[] curr = stack.pop();
            result[curr[1]] = 0;
        }
        return result;
    }

    //https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;
        int i = 0;
        ListNode node = head;
        ListNode prev = null;
        ListNode _prev = null;
        ListNode l2 = null;
        while(node != null) {
            if(i % 2 != 0) {
                if(l2 == null) {
                    l2 = node;
                }
                else {
                    prev.next = node;
                }
                _prev.next = node.next;
                node.next = null;
                prev = node;
                node = _prev.next;
            }
            else {
                _prev = node;
                node = node.next;
            }
            i++;
        }
        node = head;
        while(node.next != null) {
            node = node.next;
        }
        node.next = l2;

        return head;
    }

    //https://leetcode.com/problems/merge-in-between-linked-lists/
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int i = 0;
        ListNode node = list1;
        ListNode prev = null;
        while(i < a && node != null) {
            prev = node;
            node = node.next;
            i++;
        }
        while(i < b && node != null) {
            i++;
            node = node.next;
        }
        prev.next = list2;

        while(list2.next != null) {
            list2 = list2. next;
        }
        list2.next = node.next;

        return list1;
    }
}
