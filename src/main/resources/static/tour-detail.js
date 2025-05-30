// Khai báo biến global
let pois = [];

// Lấy tour ID từ URL
function getTourId() {
    const params = new URLSearchParams(window.location.search);
    return params.get('tourId');
}

// Load POI data từ API
async function loadPOIs() {
    const tourId = getTourId();
    if (!tourId) {
        showEmpty();
        return;
    }

    try {
        showLoading();

        // Gọi API
        const response = await fetch(`http://localhost:8082/api/listtour/${tourId}`);
        pois = await response.json(); // Gán vào biến global

        if (pois && pois.length > 0) {
            showPOIs(pois);
        } else {
            showEmpty();
        }

    } catch (error) {
        console.error('Lỗi:', error);
        showEmpty();
    }
}

// Hiển thị loading
function showLoading() {
    document.getElementById('poiList').classList.add('d-none');
    document.getElementById('empty').classList.add('d-none');
}

// Hiển thị danh sách POI
function showPOIs(poisData) {
    document.getElementById('poiList').classList.remove('d-none');
    document.getElementById('empty').classList.add('d-none');

    // Cập nhật số lượng
    document.getElementById('count').textContent = poisData.length;

    // Tạo HTML cho POI
    const html = poisData.map((poi, index) => `
    <div class="d-flex align-items-center p-3 border-bottom">
        <div class="poi-number me-3">
            ${poi.orderintour || (index + 1)}
        </div>
        <div class="flex-grow-1">
            <h6 class="mb-0">${poi.name}</h6>
            <small class="text-muted">Điểm tham quan thứ ${poi.orderintour || (index + 1)}</small>
        </div>
        <div class="action-buttons">
            <button class="btn btn-sm btn-outline-primary me-2" onclick="viewPoiDetails(${index})" title="Xem chi tiết">
                <i class="fas fa-eye"></i>
            </button>
            <button class="btn btn-sm btn-outline-danger" onclick="deletePoi(${index})" title="Xóa địa điểm">
                <i class="fas fa-trash"></i>
            </button>
        </div>
    </div>
`).join('');

    // Thêm nút thêm địa điểm ở cuối danh sách
    const addButton = `
    <div class="d-flex justify-content-center p-3">
        <button class="btn btn-primary" onclick="addNewPoi()">
            <i class="fas fa-plus me-2"></i>Thêm địa điểm mới
        </button>
    </div>
`;

    document.getElementById('poiContainer').innerHTML = html + addButton;
}

// Hiển thị empty state
function showEmpty() {
    document.getElementById('poiList').classList.add('d-none');
    document.getElementById('empty').classList.remove('d-none');
}

// Quay lại trang trước
function goBack() {
    window.history.back();
}

// Hàm xóa POI - ĐÃ SỬA
async function deletePoi(index) {
    const poi = pois[index];

    if (confirm(`Xóa "${poi.name}" khỏi tour?`)) {
        try {
            // Sửa: dùng poi.id và thêm base URL
            const response = await fetch(`http://localhost:8082/api/poioftour/${poi.id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                // Xóa khỏi mảng và cập nhật giao diện
                pois.splice(index, 1);
                showPOIs(pois); // Dùng showPOIs thay vì updatePoiList
                alert('Đã xóa POI khỏi tour thành công!');
            } else {
                alert('Lỗi khi xóa POI khỏi tour!');
            }
        } catch (error) {
            console.error('Lỗi:', error);
            alert('Lỗi kết nối!');
        }
    }
}

// Chạy khi trang load
document.addEventListener('DOMContentLoaded', loadPOIs);