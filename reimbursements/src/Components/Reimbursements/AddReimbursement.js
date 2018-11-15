import React from 'react';

export class AddReimbursement extends React.Component {
    render() {
        return (
            <form>
                <div className='form-group'>
                    <label htmlFor='amountInput'>Amount</label>
                    <input type='text' className='form-control' id='amountInput' placeholder='$00.00'/>
                </div>
                <div className='form-group'>
                    <label htmlFor='descriptionInput'>Description</label>
                    <input type='text' className='form-control' id='descriptionInput' placeholder='Description'/>
                </div>
                <div className='form-group'>
                    <div class="dropdown">
                        <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Reimbursement Type
                        </button>
                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#">Lodging</a>
                            <a class="dropdown-item" href="#">Travel</a>
                            <a class="dropdown-item" href="#">Food</a>
                            <a class="dropdown-item" href="#">Other</a>
                        </div>
                    </div>
                </div>
                <div className='form-group'>
                    {/* <Link to='/reimbursements'> */}
                        <button type="button" className="btn btn-primary">Submit</button>
                    {/* </Link> */}
                </div>
            </form>
        )
    }
}