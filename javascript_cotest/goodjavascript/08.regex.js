const regexEx = () => {
  var parse_url =
    /https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/;

  var url = "http://www.ora.com:90/goodparts?q#fragment";

  var result = parse_url.exec(url);

  var parse_number = /^-?\d+(?:\.\d*)?(?:e[+\-]?\d+)?$/i;

  var test = function (num) {
    console.log(parse_number.test(num));
  };
  test("1");
  test("98.5");
  /**
   * github - https://github.com/Guiwoo/Regex_- 페이지 참조
   *  g,m,i 모두 regex 구문 뒤에 작성할수 있음(g = 모두 다 해당 되게 서치,i = 대소문자 구별 하지않음, m= ^ $ 끝나는 라인에 일차할수 있음)
   * ?: 를 이용한 비캡쳐 그룹은 성능향상을 야기할수 있다.
   * 32개의 특수문자 [!-\/:-@\[-`{-], [^!-\/:-@\[-`{-] => 특수문자에 해당하지 않는 문자 하나 와 개꿀
   * 수량자 를 쓸떄는 ? 와 같이쓰지 말것 ex < /a별/ => aaa 를 고름 , /a별?/ => 가장 처음 해당하는 a만 리턴
   * why ? 최한의 반복만을 찾는것이 물음표 가 들은 수량자 인자
   * */
};
