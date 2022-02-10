const getData = () => {
  const https = require("https");

  const URL =
    "https://jsonmock.hackerrank.com/api/article_users?username=epaga";
  return new Promise((resolve, reject) => {
    https
      .get(URL, (res) => {
        res.on("data", (d) => {
          const me = JSON.parse(d);
          resolve(me);
        });
      })
      .on("error", (e) => reject(e));
  });
};

const main = async () => {
  res = await getData();
  console.log(res);
};
main();
