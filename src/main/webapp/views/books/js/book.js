function changePageSize(val) {
    window.location.href = "/exp3/BookServlet?type=list&pageSize=" + val;
}

function goPageNum(val) {
    //获取id为goPageNum的input里面的数值
    const num = document.getElementById("goPageNum").value;
    window.location.href = "/exp3/BookServlet?type=list&pageSize=" + val + "&pageNum=" + num;
}
