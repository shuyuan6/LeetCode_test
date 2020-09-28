public class Assignment2 {
    public static int maxSubArray(int[] nums) {
        //write your code here
        int maxSum = Integer.MIN_VALUE;
        int maxTillNow = 0;

        for (int num : nums) {
            maxTillNow = maxTillNow + num;
            if (maxSum < maxTillNow) {
                maxSum = maxTillNow;
            }
            if (maxTillNow < 0) {
                maxTillNow = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        //write your code here
        int[] nums = new int[] {-1, -2};
        System.out.println(maxSubArray(nums));

        String[] elements = "/a/b/c".split("/");
        for (String s : elements) {
            System.out.print(s + " ");
        }

    }

}
