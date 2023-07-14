const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("Twitter Contract", function() {
  let Twitter;
  let twitter;
  let owner;

  const NUM_TOTAL_NOT_MY_TWEETS = 5;
  const NUM_TOTAL_MY_TWEETS = 3;

  let totalTweets;
  let totalMyTweets;

  beforeEach(async function() {
    Twitter = await ethers.getContractFactory("TwitterContract");
    [owner, addr1, addr2] = await ethers.getSigners();
    twitter = await Twitter.deploy();

    totalTweets = [];
    totalMyTweets = [];

    for(let i=0; i<NUM_TOTAL_NOT_MY_TWEETS; i++) {
      let tweet = {
        'tweetText': 'Random text with id:- ' + i,
        'username': addr1,
        'isDeleted': false
      };

      await twitter.connect(addr1).addTweet(tweet.tweetText);
      totalTweets.push(tweet);
    }

    for(let i=0; i<NUM_TOTAL_MY_TWEETS; i++) {
      let tweet = {
        'username': owner,
        'tweetText': 'Random text with id:- ' + (NUM_TOTAL_NOT_MY_TWEETS+i),
        'isDeleted': false
      };

      await twitter.addTweet(tweet.tweetText);
      totalTweets.push(tweet);
      totalMyTweets.push(tweet);
    }
  });

  // test whether it post new tweet or not
  describe("Add Tweet", function() {

    // event eventAddTweet(address recipient, uint tweet_id);
    it("should emit AddTweet event", async function() {
      let tweet = {
        'tweet_text': 'New Tweet',
      };

      await expect(await twitter.addTweet(tweet.tweet_text)
    ).to.emit(twitter, 'eventAddTweet').withArgs(owner.address, NUM_TOTAL_NOT_MY_TWEETS + NUM_TOTAL_MY_TWEETS);
    })
  });

  // Test whether it fetchs all tweets or not
  describe("Get All Tweets", function() {
    it("should return the correct number of total tweets", async function() {
      const tweetsFromChain = await twitter.getAllTweets();
      expect(tweetsFromChain.length).to.equal(NUM_TOTAL_NOT_MY_TWEETS+NUM_TOTAL_MY_TWEETS);
    })

    it("should return the correct number of all my tweets", async function() {
      const myTweetsFromChain = await twitter.getMyTweets();
      expect(myTweetsFromChain.length).to.equal(NUM_TOTAL_MY_TWEETS);
    })
  })

  // Test wheter it Delete Tweet or not
  describe("Delete Tweet", function() {

    // event eventDeleteTweet(uint tweet_id);
    it("should emit delete tweet event", async function() {
      const TWEET_ID = 0;

      await expect(
        twitter.connect(addr1).deleteTweet(TWEET_ID)
        ).to.emit(twitter, 'eventDeleteTweet').withArgs(TWEET_ID);
    })
  })

  // Test wheter it update Tweet or not
  describe("Update Tweet", function() {
    it("should emit update tweet event", async function() {
      
      // event eventUpdateTweet(uint tweet_id, uint update_version, string text_update)
      const TWEET_ID = 1;
      const text_update = "This is an update";

      await expect(
        twitter.connect(addr1).updateTweet(TWEET_ID, text_update)
        ).to.emit(twitter, 'eventUpdateTweet').withArgs(TWEET_ID);
    })
  })
});
