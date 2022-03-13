var fs = require("fs");
var nums = parseInt(fs.readFileSync("stdin.txt").toString());
// 제출용 var nums = fs.readFileSync('/dev/stdin').toString().split("\n");

const bj10757 = () => {
  let bigger;
  let overflow = 0;
  let answer = "";

  if (a.length > b.length) {
    bigger = a.length;
    const c = a.length - b.length;
    b = "0".repeat(c) + b;
  } else {
    bigger = b.length;
    const c = b.length - a.length;
    a = "0".repeat(c) + a;
  }

  for (let i = bigger; i > 0; i--) {
    const sumA = parseInt(a[i - 1]) + parseInt(b[i - 1]) + overflow;
    answer = (sumA % 10) + answer;
    overflow = parseInt(sumA / 10);
  }
};

const bj1978 = () => {
  let count = 0;
  const isPrime = (num) => {
    if (num === 1) {
      return false;
    }
    for (let i = 2; i < num; i++) {
      if (num % i === 0) {
        return false;
      }
    }
    return true;
  };
  const numsGroup = nums[1].split(" ");
  numsGroup.map((n) => {
    if (isPrime(parseInt(n))) {
      count++;
    }
  });
  console.log(count);
};

const bj2581 = () => {
  const arr = [];
  const isPrime = (num) => {
    if (num === 1) {
      return false;
    }
    for (let i = 2; i < num; i++) {
      if (num % i === 0) {
        return false;
      }
    }
    return true;
  };
  for (let i = parseInt(nums[0]); i < parseInt(nums[1]) + 1; i++) {
    if (isPrime(i)) {
      arr.push(i);
    }
  }
  if (arr.length < 1) {
    return console.log(-1);
  }
  console.log(arr.reduce((a, b) => a + b));
  console.log(Math.min(...arr));
};

const bj11653 = () => {
  if (nums === 1) {
    return;
  }
  let i = 2;
  while (i * i <= nums) {
    if (nums % i === 0) {
      console.log(i);
      nums = Math.floor(nums / i);
    } else {
      i++;
    }
  }
  console.log(nums);
};

bj11653();
