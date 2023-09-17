var page=1
var pageNumber, totalPage, nextButton, prevButton;
window.onload = function(){
    pageNumber = document.querySelector('#log_pagenumber');
    totalPage = document.querySelector('#log_totalPage').value;
    nextButton = document.querySelector('#log_nextbutton');
    prevButton = document.querySelector('#log_prevbutton');
    getData();
    checkNextPrevButton();
}

function getData(){
    var html = '';
    var tbody = document.querySelector('#log_tbody');
    axios.get(`/mypage/api/login/${page}`)
        .then(response => {
            console.log(response.data);
            response.data.forEach(item => {
                html += '<tr>'
                html += '<td>' + item.id + '</td>'
                html += '<td>' + item.ip + '</td>'
                html += '<td>' + item.dt + '</td>'
                html += '</tr>'
            })
            tbody.innerHTML = html;
        }).catch(error => {
            console.error(error);
        });
}

function checkNextPrevButton(){
    nextButton.disabled = (totalPage <= 1) || (page >= totalPage);
    prevButton.disabled = (totalPage <= 1) || (page <= 1);
}

function prevButtonListener(){
    page-=1;
    pageNumber.innerHTML = page;
    getData();
    checkNextPrevButton();
}

function nextButtonListener(){
    page+=1;
    pageNumber.innerHTML = page;
    getData();
    checkNextPrevButton();
}