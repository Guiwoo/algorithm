//백준 2739

const a = parseInt(2);

for (let i = 1; i < 10; i++) {
  console.log(`${a} * ${i} = ${a * i}`);
}

// 백준 10950

let input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

for (let i = 1; i <= input[0]; i++) {
  let numbers = input[i].split(" ");

  console.log(Number(numbers[0]) + Number(numbers[1]));
}

//백준 14681

const a = parseInt(23);
const b = parseInt(40);
let answer = [a, b];
if (b < 45) {
  answer[1] = 60 - (45 - b);
  answer[0] === 0 ? (answer[0] = 23) : (answer[0] = answer[0] - 1);
  console.log(`${answer[0]} ${answer[1]}`);
} else {
  console.log(`${answer[0]} ${answer[1] - 45}`);
}
