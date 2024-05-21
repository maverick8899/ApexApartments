
function delPro(path, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!!!");
        });
    }
}

async function delReceipt(path) {
    if (confirm("Bạn chắc chắn muốn xóa không?")) {
        try {
            const response = await fetch(path, {
                method: "DELETE"
            });
               console.log(response);
            if (response.ok) {
                location.reload(); // Tải lại trang sau khi xóa thành công
            } else {
                throw new Error("Có lỗi xảy ra khi xóa.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi xóa.");
        }
    }
}