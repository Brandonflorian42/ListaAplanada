package edu.umg;
class ListNode {
    int val;
    ListNode prev;
    ListNode next;
    ListNode child;

    ListNode(int val) {
        this.val = val;
    }
}

public class FlattenMultilevelList {
    public ListNode flatten(ListNode head) {
        if (head == null) return null;

        ListNode curr = head;
        while (curr != null) {
            if (curr.child != null) {
                ListNode next = curr.next;
                ListNode childTail = flatten(curr.child);
                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null;

                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }
                curr = childTail;
            }
            if (curr.next == null) break;
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Crear la lista de ejemplo
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.prev = head;
        head.next.next = new ListNode(3);
        head.next.next.prev = head.next;
        head.next.next.next = new ListNode(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        head.next.next.next.child = new ListNode(7);
        head.next.next.next.child.next = new ListNode(8);
        head.next.next.next.child.next.prev = head.next.next.next.child;
        head.next.next.next.child.next.next = new ListNode(11);
        head.next.next.next.child.next.next.prev = head.next.next.next.child.next;
        head.next.next.next.child.next.next.next = new ListNode(12);
        head.next.next.next.child.next.next.next.prev = head.next.next.next.child.next.next;

        head.next.next.next.next.child = new ListNode(9);
        head.next.next.next.next.child.next = new ListNode(10);
        head.next.next.next.next.child.next.prev = head.next.next.next.next.child;

        // Aplanar la lista
        FlattenMultilevelList solution = new FlattenMultilevelList();
        ListNode flattenedHead = solution.flatten(head);

        // Imprimir la lista aplanada
        System.out.println("Lista aplanada:");
        ListNode current = flattenedHead;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
