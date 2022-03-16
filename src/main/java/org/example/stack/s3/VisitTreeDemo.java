package org.example.stack.s3;

import java.util.Stack;


public class VisitTreeDemo{
	class TreeNode{
		private int value;
		private TreeNode left;
		private TreeNode right;
		public TreeNode(int value){
		    this.value = value;	
		}
	}
    /**
     * 前序遍历二叉树
     */
	public void preOrderVisit(TreeNode node){
	    if(node== null){
	       return;	
	    }	
	    System.out.println(node.value);
	    preOrderVisit(node.left);
	    preOrderVisit(node.right);
	}
	
    /**
     * 中序遍历二叉树
     */
	public void inOrderVisit(TreeNode node){
	   if(node == null){
	      return;	
	   }	
	   inOrderVisit(node.left);
	   System.out.println(node.value);
	   inOrderVisit(node.right);
	}
   
   /**
   * 前序遍历二叉树，非递归方式
   *
   **/
    public void preOrderVisitNoCur(TreeNode node){
       	if(node == null){
       	   return;	
       	}
       	Stack<TreeNode> stack = new Stack<TreeNode>();
       	stack.push(node);
       	TreeNode cur = null;
       	while(!stack.isEmpty()){
       		cur = stack.pop();
       		System.out.println(cur.value);
       		if(cur.right != null){
       		   stack.push(cur.right);
       		}
       		if(cur.left != null){
       		  stack.push(cur.left);
       		} 	 	
       	}
    }
    
    /**
    * 中序遍历二叉树，非递归方式 
    *
    */
   public void inOrderVisitNoCur(TreeNode node){
   	if(node == null){
   	   return;	
   	}
   	Stack<TreeNode> stack = new Stack<TreeNode>();
   	while(!stack.isEmpty() || node != null){
   	   if(node != null){
   	     stack.push(node);
   	     node = node.left;	
   	   }else{
   	     node = stack.pop();
   	     System.out.println(node.value);
   	     node = node.right;	
   	   }	
   	}	
   }	
   
   
   /**
   * 后序遍历二叉树，
   *                      a
   *                    /  \
   *                  b      c
   *  结果为bca
   *思路：已有前序遍历算法，结果abc，做下调整，变成acb,然后倒序输出变成了bca
   * 因此设置两个栈，一个栈用作前序遍历，另外一个栈用于倒序输出。
   **/
   public void postOrderVisitNoCur(TreeNode node){
   	if(node == null){
   	   return;	
   	}
   	Stack<TreeNode> s1 = new Stack<TreeNode>();
   	Stack<TreeNode> s2 = new Stack<TreeNode>();
   	
   	s1.push(node);
   	while(!s1.isEmpty()){
   	   node = s1.pop();
   	   s2.push(node);
   	   if (node.left != null){
   	      s1.push(node.left);	
   	   }	
   	   if(node.right != null){
   	      s1.push(node.right);	
   	   }
   	}
   	while(!s2.isEmpty()){ 
   	   node = s2.pop();
   	   System.out.println("value=" + node.value);	
   	}
   }
	
	/**
	*  使用一个栈实现后序遍历二叉树
	*  h指向最后一次弹出打印的节点，c表示当前栈顶节点
	**/
	public void postOrderVisitTreeNoCur2(TreeNode h){
	   if(h == null){
	      return;	
	   }	
	   Stack<TreeNode> stack  = new Stack<TreeNode>();
	   stack.push(h);
	   TreeNode c = null;
	   while(!stack.isEmpty()){
	      	c = stack.peek();
	      	if(c.left != null && h != c.left && h != c.right){//处理左子树
	      	   stack.push(c.left);	
	      	}else if(c.right != null && h != c.right){
	      	  stack.push(c.right);	
	      	}{//左右子树均已经处理完成，则可以弹出
	      	   h = stack.pop();
	      	   System.out.println(h.value);	
	      	}
	   }
	}
	
	
	/**
	*计算树的高度
	*/
	public int getHeight(TreeNode h, int l){
	   if(h == null){
	      return l;	
	   }	
	   return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
	}
	
	/**
	* 设置一个二叉树每层的边界节点
	*/
	public void setEdgeMap(TreeNode h, int l, TreeNode[][] edgeMap){
	   if(h == null){
	      return;	
	   }	
	   edgeMap[l][0] = edgeMap[l][0] ==null ? h: edgeMap[l][0];
	   edgeMap[l][1] = h;
	   setEdgeMap(h.left, l + 1,edgeMap);
	   setEdgeMap(h.right, l + 1, edgeMap);
	}
	
	/**
	*打印一个不在边界map中的叶子节点
	***/
	public void printLeafNotInEdgeMap(TreeNode h, int l, TreeNode[][] edgeMap){
	   if(h == null){
	     return;	
	   }	
	   if(h.left == null && h.right == null && h != edgeMap[l][0] && h != edgeMap[l][1]){
	       System.out.println(h.value);	
	   }
	   printLeafNotInEdgeMap(h.left, l + 1, edgeMap);
	   printLeafNotInEdgeMap(h.right, l ＋1, edgeMap);
	}
	
	/**
	* 逆时针打印二叉树的边界节点
	*
	*
	**/
	public void printEdge1(TreeNode h){
	    if(h == null){
	       System.out.println(h.value);	
	    }	
	    int height = getHeight(h, 0);
	    TreeNode[][] edgeMap = new TreeNode[height][2];
	    setEdgeMap(h, 0, edgeMap);//设置树的边界节点
	    for(int i = 0; i < height; i++){
	       System.out.println(" " +edgeMap[i][0].value);//从上到下打印左边界节点值	
	    }
	    printLeafNotInEdgeMap(h,0, edgeMap);//从左到右打印叶子节点
	    for(int i = height -1; i >=0; i--){
	      if(edgeMap[i][0] != edgeMap[i][1]){
	          System.out.println(" " + edgeMap[i][1].value) //从下往上打印有边界节点值
	      }	
	    }
	}
	
	public void printLeftEdge(TreeNode h, boolean print){
		if(h == null){
		   return;	
		}
		
		if(print || (h.left == null && h.right ==null)){
		   System.out.println(h.value);	
		}
		printLeftEdge(h.left, print);
		printLeftEdge(h.right, print && h.left == null ? true:false);
	 	
	}
	
	public void printRightEdge(TreeNode h, boolean print){
	  if(h == null){
	     return;
	  }	
	  printRightEdge(h.left, print && (h.right != null ? true: false));
	  printRightEdge(h.right, print);
	  if(print ||(h.left == null && h.right == null)){
	     System.out.println(h.value);
	  }
	}
	
	/**
	* 逆时针打印二叉树的边界节点，
    *1,根节点，2，叶子节点，3,树左边界的延伸的路劲也为边界节点，4，树右边界的延伸的路劲也为边界节点。
	*
	*
	***/
	public void printEdge2(TreeNode h){
	   if(h == null){
	     System.out.println(h.value);	
	   }	
	   System.out.println(h.value);
	   if(h.left != null && h.right != null){
	      printLeftEdge(h.left,true);
	      printRightEdge(h.right, true);	
	   }else{
	      printEdge2(h.left != null ? h.left : h.right);	
	   }
	}
	
	/**
	 * 中序遍历二叉树，非递归，非栈方式，Morris算法，将左子树最右节点的右指针指向根节点
	 */
    public void morrisIn(TreeNode head){
		if(head == null){
			return;
		}
		TreeNode cur1 = head;
		TreeNode cur2 = null;
		while(cur1 != null){
			cur2 = cur1.left;
			if(cur2 != null){
				while(cur2.right != null && cur2.right != cur1){
					cur2 = cur2.right;
				}
				if(cur2.right == null){
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				}else{
					cur2.right = null;
				}
			}
			System.out.println(cur1.value);
			cur1 = cur1.right;


		}


	}

	/**
	 * 中序遍历二叉树，非递归，O（N）时间复杂度，O(1)空间复杂度
	 */
	public void morrisPre(TreeNode head){
		if(head == null){
			return;
		}
		TreeNode cur1 = head;
		TreeNode cur2 = null;
		while(cur1 != null){
			cur2 = cur1.left;
			if(cur2 != null){
				while(cur2.right != null && cur2.right != cur1){
					cur2 = cur2.right;
				}
				if(cur2.right == null){
					cur2.right = cur1;
					System.out.println(cur1.value);
					cur1 = cur1.left;
					continue;
				}else{
					cur2.right = null;
				}
			}else{
				System.out.println(cur1.value);
			}
			cur1 = cur1.right;
		}

	}

	public static void main(String[] args) {
		
	}
	
	
	
	
	
	
	
	
	
	
}
    

