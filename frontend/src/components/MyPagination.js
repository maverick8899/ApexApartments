import React, { useEffect, useState } from 'react';
import ReactPaginate from 'react-paginate';
import './paginate.css';
function MyPagination({ setPage, merchandise }) {
    const pagination = merchandise
        ? JSON.parse(localStorage.getItem('paginate.merchandise'))
        : JSON.parse(localStorage.getItem('paginate.receipt'));
    console.log('###########', pagination);
    const [itemOffset, setItemOffset] = useState(pagination.pageSize);

    function handleClick(e) {
        setPage(+e.selected + 1);
    }
    // const endOffset = itemOffset + pagination.pageSize;
    // console.log(`Loading items from ${itemOffset} to ${endOffset}`);
    const currentItems = pagination.page;
    const pageCount = Math.ceil(pagination.quantity / pagination.pageSize);

    return (
        <>
            {/* <Items currentItems={currentItems} /> */}
            <ReactPaginate
                breakLabel="..."
                nextLabel="next >"
                onPageChange={handleClick}
                pageRangeDisplayed={5}
                pageCount={pageCount}
                previousLabel="< previous"
                pageClassName="page-item"
                pageLinkClassName="page-link"
                previousClassName="page-item"
                previousLinkClassName="page-link"
                nextClassName="page-item"
                nextLinkClassName="page-link"
                breakClassName="page-item"
                breakLinkClassName="page-link"
                containerClassName="pagination"
                activeClassName="active"
            />
        </>
    );
}

export default MyPagination;
