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
  if (n === 1) {
    return;
  }
  let i = 2;
  while (!(i * i > n)) {
    if (n % i === 0) {
      n = Math.floor(n / i);
      console.log(i);
    } else {
      i++;
    }
  }
  console.log(n);
};

//에라토네스 체 구현하기
const bj1929 = () => {
  const [n, m] = nums;
  let arr = Array(+m + 1).fill(true);
  (arr[0] = false), (arr[1] = false);

  for (let i = 2; i * i < +m + 1; i++) {
    if (arr[i]) {
      for (let j = i + i; j < arr.length; j = j + i) {
        arr[j] = false;
      }
    }
  }

  for (let k = n; k < +m + 1; k++) {
    if (arr[k]) {
      console.log(k);
    }
  }
};

const bj3053 = () => {
  const pi = Math.PI;
  const answer1 = (+nums) ** 2 * pi;
  const answer2 = (+nums) ** 2 * 2;
  console.log(answer1.toFixed(6));
  console.log(answer2.toFixed(6));
};

const bj10872 = () => {
  const fct = (n) => {
    if (n < 1) {
      return 1;
    }
    return n * fct(n - 1);
  };
  console.log(fct(+nums));
};

const bj10870 = () => {
  const fibo = (n) => {
    if (n === 0) return 0;
    else if (n === 1) return 1;
    return fibo(n - 2) + fibo(n - 1);
  };
  console.log(fibo(+nums));
};

const bj11729 = () => {
  answer = [];
  const hanoi = (n, from, to, sub) => {
    if (n < 2) {
      answer.push(`${from} ${to}`);
      return;
    }
    hanoi(n - 1, from, sub, to);
    answer.push(`${from} ${to}`);
    hanoi(n - 1, sub, to, from);
  };
  console.log(2 ** +nums - 1);
  hanoi(+nums, 1, 3, 2);
  console.log(answer.join("\n"));
};

bj11729();
