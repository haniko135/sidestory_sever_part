function deleteNovel(id)
{
    var Request = new XMLHttpRequest();
    Request.open("DELETE", "/deleteNovel?novelId=" + id, true);
    Request.send();
}
function deletePage(id)
{
    var Request = new XMLHttpRequest();
    Request.open("DELETE", "/deletePage?pageId=" + id, true);
    Request.send();
}
function deleteUser(id)
{
    var Request = new XMLHttpRequest();
    Request.open("DELETE", "/deleteUser?userId=" + id, true);
    Request.send();
}
