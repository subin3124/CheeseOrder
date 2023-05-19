import React from "react";
import _ from "lodash";
import { Responsive, WidthProvider } from "react-grid-layout";
import RequestObject from "./RequestObject";
const ResponsiveReactGridLayout = WidthProvider(Responsive);

export default class DragFromOutsideLayout extends React.Component {
    static defaultProps = {
        className: "layout",
        rowHeight: 30,
        onLayoutChange: function() {},
        cols: { lg: 12, md: 10, sm: 6, xs: 4, xxs: 2 },
    };
    data=new Object();
    obj = new RequestObject();
    state = {
        shopId:"0001",
        floor: 1,
        currentBreakpoint: "lg",
        preventCollision: true,
        compactType: "horizontal",
        horizontalCompact: false,
        verticalCompact: false,
        mounted: false,
        layouts: { lg: generateLayout() }
    };

    componentDidMount() {
        this.setState({ mounted: true });
    }

    generateDOM() {
        return _.map(this.state.layouts.lg, function(l, i) {
            return (
                <div key={l.i} style={{backgroundColor:"gray"}}>
                    {!l.isConstruct ? (
                        <div >
                            <span className="text">{l.i}</span><br/>
                            <span className="text">{l.name}</span>
                        </div>
                    ) : (
                        <div>
                            <span className="text">{l.i}</span><br/>
                            <span className="text">{l.name}</span>
                        </div>
                    )}
                </div>
            );
        });
    }
    async getTablelist() {
        await this.obj.getTableList(0, 0).then(()=> this.onNewLayout());
    }
    onBreakpointChange = breakpoint => {
        this.setState({
            currentBreakpoint: breakpoint
        });
    };

    onCompactTypeChange = () => {
        const { compactType: oldCompactType } = this.state;
        const compactType = " "
        this.setState({ compactType });
    };

    onLayoutChange = (layout, layouts) => {
        this.props.onLayoutChange(layout, layouts);
    };

    onNewLayout = () => {
        this.setState({
            layouts: { lg: generateLayout() }
        });
    };

    constructor(props, context) {
        super(props, context);
        this.getTablelist();
    }

    onDrop = async (layout, layoutItem, _event) => {
        //  alert(`Dropped element props:\n${JSON.stringify(layoutItem, ['x', 'y', 'w', 'h'], 2)}`);
        let tableId = prompt("테이블 아이디를 입력하세요.");
        tableId = this.state.shopId + this.state.floor + tableId;
        let Name = prompt("테이블 이름을 입력하세요.");
        let tableInfo = {
            positionX: layoutItem.x,
            positionY: layoutItem.y,
            sizeX: layoutItem.w,
            sizeY: layoutItem.h,
            tableId: tableId,
            tableName: Name,
            isConstruct: false,
            shopId: this.state.shopId,
            floor: this.state.floor
        }
            await this.obj.createTable(tableInfo).then(r => {
                this.getTablelist();
                this.onNewLayout();
            });




    };
    onDragStop = (layout,oldItem,newItem) => {
        let obj = new RequestObject();
        obj.moveTable(newItem.i, newItem.x, newItem.y, newItem.h, newItem.w).then( r =>{});
    }

    render() {
        return (
            <div>
                <div
                    className="droppable-element"
                    draggable={true}
                    unselectable="on"
                    style={{backgroundColor:"gray",width:100,height:50}}
                    // this is a hack for firefox
                    // Firefox requires some kind of initialization
                    // which we can do by adding this attribute
                    // @see https://bugzilla.mozilla.org/show_bug.cgi?id=568313
                    onDragStart={e => e.dataTransfer.setData("text/plain", "")}
                >
                    테이블
                </div>
                <ResponsiveReactGridLayout
                    {...this.props}
                    layouts={this.state.layouts}
                    onBreakpointChange={this.onBreakpointChange}
                    onLayoutChange={this.onLayoutChange}
                    onDrop={this.onDrop}
                    // WidthProvider option
                    measureBeforeMount={false}
                    // I like to have it animate on mount. If you don't, delete `useCSSTransforms` (it's default `true`)
                    // and set `measureBeforeMount={true}`.
                    useCSSTransforms={this.state.mounted}
                    compactType={" "}
                    verticalCompact={false}
                    onDragStop={this.onDragStop}
                    onResizeStop={this.onDragStop}
                    preventCollision={true}
                    isDroppable={true}
                >
                    {this.generateDOM()}
                </ResponsiveReactGridLayout>
            </div>
        );
    }
}

function generateLayout() {

    return _.map(RequestObject.data, function(item, i) {

    //    console.log("아이템 : "+item.positionX);
        return {
            x: item.positionX,
            y: item.positionY,
            w: item.sizeX,
            h: item.sizeY,
            i: item.tableId,
           isConstruct: item.isConstruct,
            name:item.tableName
        };
    });
}

