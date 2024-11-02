def moveZeroes(self, nums):
        # Brute force
        # for value in nums:
        #     if value == 0:
        #         nums.remove(value)
        #         nums.append(value)
                
        # return nums

        # optimized solution
        # 2 pointers approach
        
        # if list start with a 0 value, swap it with the value next to it only if that value != 0
        # one pointer points to the front elements while another points to other elements in list
        # everytime the other pointer points to a value != 0:
        # set the value of the element after value at the front pointer to be equal to the value at the other pointer
        # change the value at the other pointer to be = 0
        # increment both pointers       
        # if value at other pointer == 0, jsut move pointer one step forward

        # left = 0
        # right1 = 1

        
        # if 0 not in nums:
        #     return nums

        # while nums[0] == 0 and len(nums) > 1:
        #     #right1 = 1
            
        #     if nums[right1] != 0 and right1 < len(nums):
        #         nums[0] = nums[right1]
        #         nums[right1] = 0
            
        #     if right1 == len(nums) - 1 and nums[right1] == 0:
        #         break
        #     right1 += 1

        # for right in range(1,len(nums)):
        #     if nums[right] != 0:
        #         nums[left+1] = nums[right]
        #         nums[right] = 0
        #         left += 1
        # return nums

        left = 0

        for right in range(len(nums)):
            if nums[right] != 0:
                temp = nums[left]
                nums[left] = nums[right]
                nums[right] = temp
                left += 1
        return nums