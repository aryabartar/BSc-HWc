class Node:
    data = 0
    l_count = 0
    r_count = 0
    left = None
    right = None

    def set_data(self, data):
        self.data = data


class BST2:
    root = None
    itr = None
    req = None
    flag = None
    kk = None

    def insert_data(self, data):
        p_traverse = self.root
        current_parent = self.root

        while not p_traverse is None:
            current_parent = p_traverse
            if data < p_traverse.data:
                p_traverse.lcount = p_traverse.lcount + 1
                p_traverse = p_traverse.left
            else:
                p_traverse = p_traverse.right

        if self.root is None:
            temp_node = Node()
            temp_node.set_data(data)
            self.root = temp_node

        elif data < current_parent.data:
            temp_node = Node()
            temp_node.set_data(data)
            current_parent.left = temp_node
        else:
            temp_node = Node()
            temp_node.set_data(data)
            current_parent.right = temp_node

    def get_kth_min2(self, k):
        req = -1
        kk = k
        pTraverse = self.root
        while pTraverse:
            if pTraverse.lCount + 1 == kk:
                req = pTraverse.data
                break

            elif kk > pTraverse.lCount:

                kk = kk - (pTraverse.lCount + 1)
                pTraverse = pTraverse.right

            else:
                pTraverse = pTraverse.left

        return req

    def display2(self, root):
        if not root is None and self.flag:
            self.display2(root.right)
            self.itr += 1
            if self.itr == self.kk:
                self.req = root.data
                self.flag = False

            self.display2(root.left)


s = input()
size = int(s)
tree = BST2()
j = 0
for i in range(0, size):
    s = input().split(" ")
    if s[0] == "1":
        t = int(s[1])
        tree.insert_data(t)
        j += 1

    else:
        if j < 3:
            print("No reviews yet")
        else:
            print(tree.get_kth_min2(j - (j / 3) + 1))
