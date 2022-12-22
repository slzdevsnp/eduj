# axon fundamentals training part 1

## lab2  command model
package `command`   aggregates:
`GiftCard` , `GiftCardTransactions`  

Command handlers, event Handlers 

test `GifCardTest`   fixtures for simple commands

`HotelGiftCardApplication`  spring boot app.  
nb: the axon server must be running `../local_run_axonsrv.sh`


Axon Server by visiting http://localhost:8024

HotelGiftCard App at  http://localhost:8080


## lab3 Event Handling and projections
handle emitted events from GiftCard Aggregate, create, update the view model

ex1:

`coreapi.CardSummary`  turn it into proper JPA entity

ex2: 
`query.CardSummaryProjection`
added 3 @EventHandlers :
on CardIssuedEvent, on CardRedeemedEvent, on CardReimbursedEvent

ex3: 
add @QueryHandler methods
for `coreapi.FindCardSummariesQuery`
for `coreapi.CountCardSummariesQuery`

extra:
`gui.CardSummaryDataProvider` shows a logic in vaadin how to lazily load CardSummary

Run the app.

Observe the grid that represent changes when commands are sent.

on webUI, redeem card and reimburse card work.

## lab4  Sagas  and dealines