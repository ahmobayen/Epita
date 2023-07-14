require("@nomicfoundation/hardhat-toolbox");

require("@nomicfoundation/hardhat-chai-matchers");
require("@nomiclabs/hardhat-ethers");

require('dotenv').config();

/** @type import('hardhat/config').HardhatUserConfig */

module.exports = {
  solidity: "0.8.17",
  networks: {
    goerli: {
      url: process.env.ALCHEMY_GOERLI_HTTPS,
      accounts: [process.env.ACCOUNT_KEY],
    }
  }
};