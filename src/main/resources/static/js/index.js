document.addEventListener("DOMContentLoaded", function () {
  const selectAllElem = document.querySelector("#select-all");
  const tbodyElem = document.querySelector("#schools-body");
  const allCheckboxs = tbodyElem.querySelectorAll('input[type="checkbox"]');
  const deleteSchoolElem = document.querySelector("#delete-schools");
  const currentYearElem = document.querySelector("#current-year");

  if (currentYearElem) {
    currentYearElem.textContent = new Date().getFullYear();
  }

  function handleDeleteAll() {
    const selectedCheckedElem = Array.from(allCheckboxs).filter(
      (elem) => elem.checked
    );

    const urlSearchParam = new URLSearchParams(window.location.search);
    urlSearchParam.delete("selectedIds");

    selectedCheckedElem.forEach((elem) => {
      const name = elem.getAttribute("name");
      const value = elem.getAttribute("value");
      urlSearchParam.append(name, value);
    });

    const confirmed = window.confirm("Do you want to delete selected schools?");

    if (confirmed) {
      window.location.replace(
        "/school/deleteselected?" + urlSearchParam.toString()
      );
    }
  }

  function toggleVisibility(isChecked) {
    if (isChecked) {
      deleteSchoolElem.classList.remove("-translate-x-full", "opacity-0");
      deleteSchoolElem.classList.add("translate-x-0", "opacity-100");
    } else {
      deleteSchoolElem.classList.add("-translate-x-full", "opacity-0");
      deleteSchoolElem.classList.remove("translate-x-0", "opacity-100");
    }
  }

  function handleInputChecked(evt) {
    let isOneElemChecked = evt.target.checked;
    if (!isOneElemChecked) {
      const checkedElemIndex = Array.from(allCheckboxs).findIndex(
        (checkbox) => checkbox.checked === true
      );
      isOneElemChecked = checkedElemIndex !== -1;
    }

    if (!isOneElemChecked && selectAllElem.checked) {
      selectAllElem.checked = false;
    }

    toggleVisibility(isOneElemChecked);
  }

  function handleSelectAllChange(evt) {
    toggleVisibility(evt.target.checked);

    allCheckboxs.forEach((checkbox) => {
      if (checkbox.checked !== evt.target.checked) {
        checkbox.checked = evt.target.checked;
      }
    });
  }

  selectAllElem.addEventListener("change", handleSelectAllChange);
  allCheckboxs.forEach((checkbox) => {
    checkbox.addEventListener("change", handleInputChecked);
  });

  deleteSchoolElem.firstElementChild.addEventListener("click", handleDeleteAll);
});
