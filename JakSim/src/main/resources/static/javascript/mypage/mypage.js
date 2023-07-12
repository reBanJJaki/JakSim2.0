var nameChangeButton, telChangeButton;
var nameInput, telInput;
//animation
var textA, textB, textZ, textK;
window.onload = function() {
    var deleteButton = document.getElementById('mypage_delete');
    nameChangeButton = document.getElementById('profile_name_change');
    telChangeButton = document.getElementById('profile_tel_change');
    nameInput = document.getElementById('profile_name_input');
    telInput = document.getElementById('profile_tel_input');

    //animation
    textA = document.getElementById('textA');
    textB = document.getElementById('textB');
    textZ = document.getElementById('textZ');
    textK = document.getElementById('textK');

    setInterval(toggleText, 2000);
    //animation

    deleteButton.addEventListener('click', deleteUser);

    var isNameChange = true;
    nameChangeButton.addEventListener('click', function(){
        if(isNameChange){
            nameChangeInput();
            isNameChange = false;
        }else{
            nameChangeResult();
            isNameChange = true;
        }
    });

    var isTelChange = true;
    telChangeButton.addEventListener('click', function(){
        if(isTelChange){
            telChangeInput();
            isTelChange = false
        }else{
            checkTel();
            isTelChange = true;
        }
    })
}

function toggleText(){
    textA.classList.toggle('hidden');
    textB.classList.toggle('hidden');
    textK.classList.toggle('hidden');
    textZ.classList.toggle('hidden');
}

function telChangeInput(){
    telInput.readOnly = false;
    telInput.focus();
    telChangeButton.textContent = '확인';
}

function telChangeResult(){
    telChangeButton.textContent = '수정';
    telInput.readOnly = true;

    axios.put('/mypage/api/profile/update/tel', {tel: telInput.value})
        .then(response => {
            alert('전화번호가 정상적으로 변경되었습니다.');
            window.location.reload();
        })
        .catch(error => {
            console.error(error);
        });
}

function nameChangeResult(){
    nameChangeButton.textContent = '수정';
    nameInput.readOnly = true;

    axios.put('/mypage/api/profile/update/name', {name: nameInput.value})
        .then(response => {
            console.log(response.data);
            alert('이름이 정상적으로 변경되었습니다.');
            window.location.reload();
        })
        .catch(error => {
            console.error(error);
        });
}

function checkTel(){
    axios.post('/mypage/api/profile/check/tel', {tel: telInput.value})
            .then(response => {
                console.log(response.data);
                if(response.data === true){
                    telChangeResult();
                }else{
                    alert('이미 존재하는 전화번호입니다.');
                }
            })
            .catch(error => {
                console.error(error);
            });
}

function nameChangeInput(){
    nameInput.readOnly = false;
    nameInput.focus();
    nameChangeButton.textContent = '확인';
}

var deleteUser = function(){
    var data = {id : document.getElementById('navi_username').innerHTML};
    console.log(data);

    axios.delete('/mypage/api/delete')
        .then(response => {
            if(response.data === 1){
                alert('회원정보가 삭제되었습니다.');
                window.location.href='/logout';
            }else{
                alert('????? 와 안되누?');
            }
        })
        .catch(error => {
            console.error(error);
        });
}


