const checkPoint = document.querySelector('#editorForm');
const submitBtn = document.querySelector('#"submit__btn"');

submitBtn.addEventListener('click',()=>{
	console.log('test');
    checkPoint.submit();
});