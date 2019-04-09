function addProduct() {
    var newProduct = document.createElement('div');
    var selectHTML = '<input th:field=\"${mealproduct.name}\" id=\"mealproduct\" class=\"form-control\">';
    var values = ['GRAM', 'AMOUNT', 'MILLIMETERS', 'TSP', 'TBSP'];
    var displays = ['gramów', 'sztuk', 'mililitrów', 'łyżeczek', 'łyżek'];
    selectHTML="<select>";
    for(i = 0; i < values.length; i++) {
        selectHTML += "<option value='" + values[i] + "'>" + displays[i] + "</option>";
    }
    selectHTML += "</select>";
    newProduct.innerHTML = selectHTML;
    document.getElementById("additionalProducts").appendChild(newProduct);
}
