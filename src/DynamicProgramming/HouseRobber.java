package DynamicProgramming;
//https://leetcode.com/problems/house-robber/description/
class HouseRobber {
    public int rob(int[] nums) {
        SumData[] sumsData =  new SumData[nums.length];
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        SumData sd = new SumData() ;
        sd.sum = nums[0];
        sd.fromFirst = true;
        sumsData[0] = sd;
		
        if (nums[0] > nums[1]) {
			sumsData[1] = sumsData[0];
		} else {
            sd = new SumData() ;
            sumsData[1] = sd;
			sd.sum = nums[1];
			sd.fromFirst = false;
        }
        
        for (int i = 2; i < nums.length; i ++) {
            int sumWithoutPrevHouse = sumsData[i-2].sum + nums[i];
            if (sumsData[i-1].sum > sumWithoutPrevHouse) {
                sumsData[i] = sumsData[i-1];
			} else {
				SumData sumData = new SumData();
				sumData.sum = sumsData[i-2].sum + nums[i];
				sumData.fromFirst = sumsData[i-2].fromFirst;
				sumsData[i] = sumData;
			}
        }
		// if the last element uses sum from the first element we cannot use it.
		if (sumsData[nums.length -1].fromFirst == false) {
			return sumsData[nums.length - 1].sum;
		} else {
			return sumsData[nums.length - 2].sum;
		}
	}
    
    class SumData {
        int sum;
        boolean fromFirst;
    }
}