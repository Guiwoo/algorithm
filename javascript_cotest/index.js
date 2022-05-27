const draw = (n, y, x) => {
  if (n == 1) return 1;
  if (y < n / 2 && x < n / 2)
    return Math.pow(n / 2, 2) + draw(n / 2, x % (n / 2), y % (n / 2));
  if (y < n / 2 && x >= x / 2) return draw(n / 2, x % (n / 2), y % (n / 2));
  if (y >= n / 2 && x < n / 2)
    return Math.pow(n / 2, 2) * 2 + draw(n / 2, x % (n / 2), y % (n / 2));
  if (y >= n / 2 && x >= n / 2)
    return Math.pow(n / 2, 2) * 3 + draw(n / 2, x % (n / 2), y % (n / 2));
};
console.log(draw(512, 0, 0));
