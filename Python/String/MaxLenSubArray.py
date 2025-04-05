def lengthOfLongestSubstring(self, s):
        frst_ptr, max_len = 0, 0
        indexes = {}  # Hash table to store last index of characters

        for scnd_ptr in range(len(s)):
            char = s[scnd_ptr]
            # Check if character already exists in window and update left pointer
            if char in indexes and indexes[char] >= frst_ptr:
                frst_ptr = max(frst_ptr, indexes[char] + 1)
            # Update char_index and track maximum length
            indexes[char] = scnd_ptr
            max_len = max(max_len, scnd_ptr - frst_ptr + 1)

        return max_len