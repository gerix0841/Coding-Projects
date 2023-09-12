class InlineWordEditor extends HTMLElement {
    constructor() {
        super();

        const newForm = document.createElement("form");
        const newSpan = document.createElement("span");
        const newInput = document.createElement("input");

        this.newInput.innerHTML = this.textContent;
        this.newSpan.value = this.textContent;

        this.newSpan.appendChild(this.newInput);
        this.newForm.appendChild(this.newSpan);

        this.newForm.style.display = 'inline-block';
        this.newSpan.style.backgroundColor = 'green';

        const shadowRoot = this.attachShadow({ mode: 'open' });
        shadowRoot.appendChild(newForm);

        this.newSpan.addEventListener('click', e => {
            e.style.display = 'none';
            newSpan.style.display = 'inline';
        });

        this.newInput.addEventListener('click', e => {
            e.style.display = 'none';
            newInput.style.display = 'inline';
        });
    }

}

customElements.define('inline-word-editor', InlineWordEditor);
