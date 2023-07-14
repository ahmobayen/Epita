// SPDX-License-Identifier: GPL-3.0

pragma solidity 0.8.17;
/**
 * @title Twitter Contract
 * @dev Store & retrieve value in a variable
 */
contract TwitterContract {

    event eventAddTweet(address recipient, uint tweet_id);
    event eventDeleteTweet(uint tweet_id);
    event eventUpdateTweet(uint tweet_id);

    struct Tweet {
        uint id;
        address username;
        string tweet_text;
        bool is_deleted;
    }

    Tweet[] private tweets;

    // Mapping tweet to the wallet address of the user
    mapping(uint256 => address) map_tweet_to_owner;

    // Method to be called by our frontend when trying to add a new Tweet
    function addTweet(string memory tweet_text) public {
        uint tweet_id = tweets.length;
        bool is_deleted = false;

        tweets.push(Tweet(tweet_id, msg.sender, tweet_text, is_deleted));
        map_tweet_to_owner[tweet_id] = msg.sender;
        emit eventAddTweet(msg.sender, tweet_id);
    }

    // Method to fetch all Tweets
    function getAllTweets() external view returns (Tweet[] memory) {
        Tweet[] memory temporary = new Tweet[](tweets.length);
        uint counter = 0;
        for(uint i=0; i<tweets.length; i++) {
            if(tweets[i].is_deleted == false) {
                temporary[counter] = tweets[i];
                counter++;
            }
        }

        Tweet[] memory result = new Tweet[](counter);
        for(uint i=0; i<counter; i++) {
            result[i] = temporary[i];
        }
        return result;
    }

    // Method to fetch owners Tweets
    function getMyTweets() external view returns (Tweet[] memory) {
        Tweet[] memory temporary = new Tweet[](tweets.length);
        uint counter = 0;
        for(uint i=0; i<tweets.length; i++) {
            if(map_tweet_to_owner[i] == msg.sender && tweets[i].is_deleted == false) {
                temporary[counter] = tweets[i];
                counter++;
            }
        }

        Tweet[] memory result = new Tweet[](counter);
        for(uint i=0; i<counter; i++) {
            result[i] = temporary[i];
        }
        return result;
    }

    // Method to Delete a Tweet
    function deleteTweet(uint tweet_id) public {
        bool is_deleted = true;

        if(map_tweet_to_owner[tweet_id] == msg.sender) {
            tweets[tweet_id].is_deleted = is_deleted;
            emit eventDeleteTweet(tweet_id);
        }
    }

    // Method to update a Tweet    
    function updateTweet(uint tweet_id, string memory update_text) external {
        if(map_tweet_to_owner[tweet_id] == msg.sender) {
            tweets[tweet_id].tweet_text = update_text;
            emit eventUpdateTweet(tweet_id);
        }
    }
}
