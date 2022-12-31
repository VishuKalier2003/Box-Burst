/* You are given several boxes with different colors represented by different positive numbers... You may experience several rounds to remove boxes until there is no box left... Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points... Return the maximum points you can get...
 * Eg 1: Input: boxes = [1,3,2,2,2,3,4,3,1] Output: 23
        Explanation:
                    [1, 3, 2, 2, 2, 3, 4, 3, 1] 
                    ----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
                    ----> [1, 3, 3, 3, 1] (1*1=1 points) 
                    ----> [1, 1] (3*3=9 points) 
                    ----> [] (2*2=4 points)
 */
import java.util.*;
public class BoxBurst 
{
    public int[][] Evaluate(int key[][], Vector<Integer> vec, int index)
    {
        int value = vec.get(index);
        int count = 0, pos = 0, c = 0;
        for(int i = 0; i < vec.size(); i++)
        {
            if(vec.get(i) == value)
                count++;
            if(key[0][i] == 0 && c == 0)
            {
                pos = i;
                c = 1;
            }
        }
        key[0][pos] = value;
        key[1][pos] = count;
        return key;
    }
    public int[][] GetEvaluation(int key[][], Vector<Integer> vec)
    {
        for(int i = 0; i < vec.size(); i++)
        {
            if(Check(key, vec.get(i)) == true)
                key = Evaluate(key, vec, i);
        }
        return key;
    }
    public boolean Check(int key[][], int val)
    {
        int j = 0, loop = 0;
        boolean confirmed = false;
        do
        {
            if(key[0][j] != val && key[0][j] != 0)
                j++;
            else if(key[0][j] == val)
            {
                confirmed = false;
                loop = 1;
            }
            else if(key[0][j] == 0)
            {
                confirmed = true;
                loop = 1;
            }
        }while(loop != 1);
        return confirmed;
    }
    public int PrintDynamicArray(int key[][], Vector<Integer> vec)
    {
        int size = 0;
        System.out.println("The Dynamic Programming (DP) Matrix formed ");
        for(int j = 0; j < vec.size(); j++)
        {
            if(key[0][j] == 0)
            {
                size = j;
                break;
            }
            else
                System.out.print("Number : "+key[0][j]+", Occurrence : "+key[1][j]);
            System.out.println();
        }
        return size;
    }
    public int GetMaximumPoints(int dp[][], Vector<Integer> vec, int size)
    {
        Vector<Integer> list = new Vector<Integer>();
        int i = 0, loop = 0, j = 0, val = 0;
        do
        {
            int x1 = vec.get(i);
            for(int k = 0; k < size; k++)
            {
                if(dp[0][k] == x1)
                    val = dp[1][k];
            }
            if(vec.get(i+1) == x1)
            {
                list.add(j, x1);
                j++;
                i++;
            }
            if(list.size()-1 == val)
            {
                for(int k = 0; k < val; k++)
                    vec.removeElementAt(i-val);
                i = 0;
                j = 0;
                System.out.println(vec);
                list.removeAllElements();
            }
            if(vec.isEmpty())
                loop = 1;
        }while(loop == 0);
        return 0;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x, a;
        System.out.print("Enter Size of the Vector list : ");
        x = sc.nextInt();
        Vector<Integer> boxes = new Vector<Integer>(x);
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter "+(i+1)+" data : ");
            a = sc.nextInt();
            boxes.add(i, a);
        }
        BoxBurst burst = new BoxBurst();
        int Key[][] = new int[2][x];
        Key = burst.GetEvaluation(Key, boxes);
        x = burst.PrintDynamicArray(Key, boxes);
        a = burst.GetMaximumPoints(Key, boxes, x);
        sc.close();
    }
}

// Time Complexity -   O(n^3) time...
// Space Complexity -  0(n^3) space...