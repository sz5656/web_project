let btn2 = document.querySelector('#addBtn');
btn2.addEventListener('click',function() {
  let obj  = {id : document.querySelector('#id').value, 
              name : document.querySelector('#name').value,
              point : document.querySelector('#point').value};
  let tr = document.createElement('tr');
  for (const k in obj) {
    let td = document.createElement('td');
    td.innerHTML = obj[k];
    tr.appendChild(td);
  }
  // obj.forEach(ele => {
  //   let td = document.createElement('td');
  //   td.innerHTML(ele);
  //   tr.appendChild(td);
  // });
  document.querySelector('#list').appendChild(tr);

  document.querySelector('#id').value = '';
  document.querySelector('#name').value = '';
  document.querySelector('#point').value = '';
});
