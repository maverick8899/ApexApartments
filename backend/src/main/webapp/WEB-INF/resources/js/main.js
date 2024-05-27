
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

document.addEventListener('DOMContentLoaded', function() {
    const customerId = document.getElementById('customerId').value;

    document.querySelectorAll('.update-btn').forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();

            const merchandiseId = this.getAttribute('data-id');
            const apiUrl = `http://localhost:8080/apartment_manager/api/notify/${customerId}/${merchandiseId}`;

            fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    message: 'Cập nhật thông tin hàng hóa'
                })
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Success:', data);
                    // Thực hiện các hành động khác sau khi cập nhật thành công
                })
                .catch((error) => {
                    console.error('Fetch error:', error);
                });
        });
    });
});


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