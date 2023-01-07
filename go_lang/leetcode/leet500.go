package main

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// 572번 트리문제 흠 나쁘지않은데 ? 재귀로 도는거 ?
func isSubtree(root *TreeNode, subRoot *TreeNode) bool {
	DFSfunc := func(*TreeNode, *TreeNode, func(*TreeNode, *TreeNode) bool) bool { return true }
	compareTree := func(*TreeNode, *TreeNode) bool { return true }

	DFSfunc = func(s *TreeNode, t *TreeNode, f func(*TreeNode, *TreeNode) bool) bool {
		if s == nil {
			if t == nil {
				return true
			}
			return false
		}
		if f(s, t) == true {
			return true
		}
		if DFSfunc(s.Left, t, f) == true {
			return true
		}
		return DFSfunc(s.Right, t, f)
	}

	compareTree = func(t1 *TreeNode, t2 *TreeNode) bool {
		if t1 == nil {
			if t2 == nil {
				return true
			}
			return false
		} else if t2 == nil {
			return false
		}
		if t1.Val != t2.Val {
			return false
		}
		if !compareTree(t1.Left, t2.Left) {
			return false
		}
		return compareTree(t1.Right, t2.Right)
	}
	return DFSfunc(root, subRoot, compareTree)
}
