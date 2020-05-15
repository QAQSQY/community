function ajaxfasong() {


    let questionId = $('#question_id').val();
    let textareaId = $('#textarea_id').val();
    $.ajax({
        type: 'post',
        url: '/comment',
        contentType:'application/json;charset=UTF-8',
        data: JSON.stringify({
            "parentId": questionId,
            "content": textareaId,
            "type": 1
        }),
        dataType: 'json',
        success: function (response) {
            console.log(response)
        },
        error: function () {

        }
    });
}