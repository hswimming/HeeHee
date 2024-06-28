//table 동적 업데이트 Ajax용

$(document).ready(function() {
    // 페이지 로드 시 테이블을 초기화
    loadTable();

    // 검색 버튼 클릭 시
    $('#searchButton').click(function() {
        loadTable();
    });

    // 초기화 버튼 클릭 시
    $('#resetButton').click(function() {
        $('#searchInput').val('');
        loadTable();
    });

    // 테이블 데이터를 Ajax로 가져오기
    function loadTable() {
        var category = $('#searchCategory').val();
        var keyword = $('#searchInput').val();

        $.ajax({
            url: '/heehee/admin/searchAuctionDetail',
            method: 'GET',
            data: { 'category': category, 
            		'keyword': keyword 
            		 },
            success: function(data) {
                var tableBody = $('#tableBody');
                tableBody.empty();

                data.forEach(function(item) {
                    var row = `<tr>
                        <td><input type="checkbox" class="rowCheckbox" data-id="${item.product_seq}"></td>
                        <td>${item.product_seq}</td>
                        <td>${item.category}</td>
                        <td>${item.detail_category}</td>
                        <td>${item.seller_id}</td>
                        <td>${item.auction_title}</td>
                        <td>${new Date(item.exp_time).toLocaleDateString()}</td>
                        <td>${item.auc_status}</td>
                    </tr>`;
                    tableBody.append(row);
                });
            },
            error: function(xhr, status, error) {
                alert('데이터를 가져오는 중 오류가 발생했습니다.');
            }
        });
    }

    // 수정 버튼 클릭 시
    $('#editButton').click(function() {
        var selected = getSelectedRow();

        if (selected.length === 1) {
            var row = selected.closest('tr');
            var id = selected.attr('data-id');

            // 수정할 내용 입력란을 추가
            if (row.next().hasClass('banReason')) {
                row.next().next().remove();
                row.next().remove();
            } else {
				$.ajax({
					url: '/heehee/admin/getAucBanReason',
					method:'GET',
					data:{ 'product_seq': id
							},
					success: function(contentData){
	                var editRow = `
	                    <tr class="editRow">
							<td colspan="8">
							    <div class="updateContainer">
							        <p class="productUpdate">판매상태<br>수정</p>
							    </div>
							    <select id="editStatus${id}">
							        <option value="입찰" ${row.find('td').eq(7).text() === '입찰' ? 'selected' : ''}>입찰</option>
							        <option value="판매중지" ${row.find('td').eq(7).text() === '판매중지' ? 'selected' : ''}>판매중지</option>
							    </select>
							    <input type="text" id="editInput${id}" class="singleInput" value="${row.find('td').eq(5).text()}" placeholder="판매중지 사유를 입력해주세요">
							    <button class="saveEditButton" data-id="${id}">수정 등록</button>
							</td>
	                    </tr>`;
	                    
					var banReason = `
						<tr class="banReason">
							<td colspan="6">
		                    	<div class="aucBanReason">
									<p class="productUpdate">판매중지 사유</p>
									<p>${contentData.auc_ban_reason}</p>
								</div>
							</td>
						</tr>
	                    `;
	                row.after(editRow);
	                row.after(banReason);
					},
					error: function(xhr, status, error){
						alert('정지사유를 가져오는 중 오류가 발생했습니다.');
					}
				});
            }
        } else if (selected.length === 0) { 
        	alert('수정할 항목을 선택해주세요');
        } else {
            alert('수정할 항목을 하나만 선택해주세요');
        }
    });

    // 저장 버튼 클릭 시
    $(document).on('click', '.saveEditButton', function() {
        var product_seq = $(this).attr('data-id');
        var auc_status = $(`#editStatus${product_seq}`).val();
        var auc_ban_reason = $(`#editInput${product_seq}`).val();
        var curr_auc_status = getSelectedRow().closest('tr').find('td').eq(7).text();

		console.log('경매상태 반환 테스트 : ' + curr_auc_status);
		
		if(curr_auc_status == '낙찰' || curr_auc_status == '거래완료'){
			alert('거래상태를 변경할 수 없습니다.');
		} else {
	        $.ajax({
	            url: '/heehee/admin/updateAucStatus',
	            method: 'POST',
	            data: { 'product_seq': product_seq, 
	            		'auc_status': auc_status, 
	            		'auc_ban_reason':auc_ban_reason  
	            		},
	            success: function() {
	                loadTable();
	            },
	            error: function() {
	                alert('등록 중 오류가 발생했습니다.');
	            }
	        });
		}
    });

    // 삭제 버튼 클릭 시
    $('#deleteButton').click(function() {
        var selected = getSelectedRow();

        if (selected.length > 0) {
            if (confirm('선택된 항목을 삭제하시겠습니까?')) {
                selected.each(function() {
                    var product_seq = $(this).attr('data-id');

                    $.ajax({
                        url: '/heehee/admin/deleteAuction',
                        method: 'POST',
                        data:{'product_seq': product_seq 
                        		},
                        success: function() {
                            loadTable();
                        },
                        error: function() {
                            alert('삭제 중 오류가 발생했습니다.');
                        }
                    });
                });
            }
        } else {
            alert('삭제할 항목을 선택해주세요');
        }
    });

    function getSelectedRow() {
        return $('input.rowCheckbox:checked');
    }
});