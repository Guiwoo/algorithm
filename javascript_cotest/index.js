// 백준 8393

const fs = require("fs");
// const stdin = fs.readFileSync("/dev/stdin").toString().split("\n");

// const input = (() => {
//   let line = 0;
//   return () => stdin[line++];
// })();

// const input = `5
// 1 1
// 12 34
// 5 500
// 40 60
// 1000 1000`.split("\n");
// input.map((e, index) => {
//   if (index != 0) {
//     const [a, b] = e.split(" ");
//     console.log(parseInt(a) + parseInt(b));
//   }
// });
// console.log(input);

// let input = 5;
// let answer = "";
// for (let i = 0; i < input; i++) {
//   answer = `${answer}${i + 1}\n`;
// }
// console.log(answer);

const abc = [0, 0, 0, 1, 0, 0];

const test = abc.map((e, index) => {
  if (e === 0 && abc[index + 1] === 0 && abc[index + 2] === 0) {
    abc[index + 1] = 1;
    abc[index + 2] = 1;
  }
  return e;
});
console.log(test);
