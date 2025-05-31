let allPois = [];
let filteredPois = [];

// Load POI
async function loadAllPois() {
    try {
        showLoading();
        const response = await fetch('http://localhost:8082/api/poi/all');
        allPois = await response.json();

        console.log('Loaded POIs:', allPois); // Debug

        if (allPois.length > 0) {
            filteredPois = [...allPois];
            renderPois();
            showPoiGrid();
        } else {
            showEmpty();
        }
    } catch (error) {
        console.error('Lỗi:', error);
        showEmpty();
    }
}

// Show states
function showLoading() {
    document.getElementById('loading').classList.remove('d-none');
    document.getElementById('poiGrid').classList.add('d-none');
    document.getElementById('empty').classList.add('d-none');
}

function showPoiGrid() {
    document.getElementById('loading').classList.add('d-none');
    document.getElementById('poiGrid').classList.remove('d-none');
    document.getElementById('empty').classList.add('d-none');
}

function showEmpty() {
    document.getElementById('loading').classList.add('d-none');
    document.getElementById('poiGrid').classList.add('d-none');
    document.getElementById('empty').classList.remove('d-none');
}

// Render POIs
function renderPois() {
    const html = filteredPois.map(poi => `
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card poi-card h-100 shadow-sm">
                <div class="position-relative">
                    <img src="${poi.imageUrl || 'https://via.placeholder.com/400x200/e9ecef/6c757d?text=Chưa+có+ảnh'}" 
                         class="card-img-top poi-image" alt="${poi.name}">
                    <div class="position-absolute top-0 start-0 m-2">
                        <span class="badge bg-primary rounded-pill px-3">${poi.typename || 'Chưa phân loại'}</span>
                    </div>
                    <div class="position-absolute top-0 end-0 m-2">
                        <button class="btn btn-danger btn-sm rounded-circle" onclick="deletePoi(${poi.id})" title="Xóa POI">
                            <i class="fas fa-trash"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title text-primary fw-bold mb-2">${poi.name}</h5>
                    <div class="mb-2">
                        <small class="text-muted">
                            <i class="fas fa-map-marker-alt text-danger me-1"></i>
                            ${poi.address || 'Chưa có địa chỉ'}
                        </small>
                    </div>
                    <div class="mb-2">
                        <small class="text-info">
                            <i class="fas fa-clock me-1"></i>
                            ${poi.openTime || 'N/A'} - ${poi.closeTime || 'N/A'}
                        </small>
                    </div>
                    <p class="card-text text-secondary flex-grow-1 mb-3">
                        ${(poi.description || 'Chưa có mô tả').substring(0, 100)}${poi.description && poi.description.length > 100 ? '...' : ''}
                    </p>
                </div>
            </div>
        </div>
    `).join('');

    document.getElementById('poiContainer').innerHTML = html;
    document.getElementById('totalCount').textContent = filteredPois.length;
}

// Actions
function viewDetail(id) {
    window.location.href = `poi-detail.html?poiId=${id}`;
}



async function deletePoi(id) {
    const poi = allPois.find(p => p.id === id);
    if (!confirm(`Xóa "${poi.name}"?`)) return;

    try {
        const response = await fetch(`http://localhost:8082/api/poi/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Xóa thành công!');
            loadAllPois();
        } else {
            alert('Lỗi xóa!');
        }
    } catch (error) {
        console.error('Lỗi:', error);
        alert('Lỗi kết nối!');
    }
}

function addNewPoi() {
    window.location.href = 'add-poi.html';
}
//loc poi
async function filterPois() {
    const type = document.getElementById('typeFilter').value;

    console.log('Filter by type:', type); // Debug

    try {
        showLoading(); // Hiển thị loading

        let pois = [];

        if (type) {
            // Gọi API lọc theo typename - SỬA ENDPOINT
            console.log('Calling API:', `http://localhost:8082/api/poi/typename/${encodeURIComponent(type)}`);
            const response = await fetch(`http://localhost:8082/api/poi/typename?typename=${encodeURIComponent(type)}`);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            pois = await response.json();
            console.log('Filtered POIs:', pois); // Debug
        } else {
            // Nếu không chọn loại, hiển thị tất cả
            pois = [...allPois];
        }

        filteredPois = pois;
        renderPois();
        showPoiGrid();

    } catch (error) {
        console.error("Lỗi khi lọc POI:", error);
        showEmpty();
    }
}

function goBack() {
    window.history.back();
}

// Start
document.addEventListener('DOMContentLoaded', loadAllPois);
