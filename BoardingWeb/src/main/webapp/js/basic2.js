let students = [
  {sno: 111, sname: '김민지', score: 90},
  {sno: 112, sname: '홍길동', score: 80},
  {sno: 113, sname: '박문수', score: 70}
];
let table = document.createElement('table');
table.setAttribute('border','2');
for (const ele of students) {
  let tr = document.createElement('tr');
  for (const prop in ele) {
    let td = document.createElement('td');
    td.innerHTML = ele[prop];
    console.log('속성: ' + prop + ',값: ' + ele[prop]);
    tr.appendChild(td);
  }
  table.appendChild(tr);
  console.log("-------------------------------");
}
document.querySelector('#show').appendChild(table);
